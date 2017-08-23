package com.dragneelfps.timetable

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by srawa on 8/14/2017.
 */
object Util {
    /*
    *   Util class
     */

    //get the date in string format of HH:mm eg. 09:00
    fun getTime(date : Date): String{
        var sdf = SimpleDateFormat("HH:mm")
        val time : String = sdf.format(date)
        return time
    }
    //get the date in string format but only used for sample
    fun getTime(dateInt: Int): String  = "$dateInt:00"
}
object DatabaseContract  {
    class TimeTableEntry : BaseColumns {
        companion object {
            /*
            *   Details of the table
             */
            val TABLE_NAME = "TimeTable"
            val COLUMN_NAME_DAY = "Day"
            val COLUMN_NAME_START_TIME = "StartTime"
            val COLUMN_NAME_END_TIME = "EndTime"
            val COLUMN_NAME_TYPE = "Type"
            val COLUMN_NAME_SUBJECT = "Subject"
            val COLUMN_NAME_SUBJECT_SHORT = "SubjectShort"
            val COLUMN_NAME_TEACHER = "Teacher"
            val COLUMN_NAME_LOCATION = "Location"
            val COLUMN_NAME_NOTES = "Notes"
            //sql query to create the table
            @JvmField val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_DAY + " TEXT," +
                    COLUMN_NAME_START_TIME + " DATE," +             //Format: HH:MI
                    COLUMN_NAME_END_TIME + " DATE," +
                    COLUMN_NAME_TYPE + " TEXT," +
                    COLUMN_NAME_SUBJECT + " TEXT," +
                    COLUMN_NAME_SUBJECT_SHORT + " TEXT," +
                    COLUMN_NAME_TEACHER + " TEXT," +
                    COLUMN_NAME_LOCATION + " TEXT," +
                    COLUMN_NAME_NOTES + " TEXT)"
            //sql query to empty the table
            @JvmField val SQL_DELETE_TABLE_ROWS = "DELETE FROM " + TABLE_NAME
            @JvmField val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        }
    }
}


object SampleCreater{

    //Create random sample for the database
    fun create(context: Context){
        val days = arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday")
        val subjects = arrayOf("Computer Graphics","Pattern","Java","Theroy of Commputation")
        val subjects_short = arrayOf("CG","PR","Java","TOC")
        val types = arrayOf("Lab","Theory")
        val teachers = arrayOf("Anil","Swati","Mahesh","Roy")
        val locations = arrayOf("TW1FF1","TW2TF3","SPS14","TW3GF1")
        val notes = "Loren Ipsum"

        var valuesList = ArrayList<ContentValues>()
        var rd = Random()
        for(i in 1..10){
            var values = ContentValues()
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_DAY,days[rd.nextInt(days.size)])
            val startTime = rd.nextInt(12) + 1
            var endTime =  if(startTime < 12) (startTime + 1) else 1
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_START_TIME,Util.getTime(startTime))
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_END_TIME, Util.getTime(endTime))
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_TYPE,types[rd.nextInt(types.size)])
            val sb = rd.nextInt(subjects.size)
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT,subjects[sb])
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT_SHORT,subjects_short[sb])
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_TEACHER,teachers[rd.nextInt(teachers.size)])
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_LOCATION,locations[rd.nextInt(locations.size)])
            values.put(DatabaseContract.TimeTableEntry.COLUMN_NAME_NOTES,notes)
            Log.d("debug",values.toString())
            valuesList.add(values)
        }

        var mDbHelper = TimeTableDbHelper(context)
        var db = mDbHelper.writableDatabase
        //db.execSQL(DatabaseContract.TimeTableEntry.SQL_DELETE_TABLE_ROWS)
        db.execSQL(DatabaseContract.TimeTableEntry.SQL_DROP_TABLE)
        db.execSQL(DatabaseContract.TimeTableEntry.SQL_CREATE_ENTRIES)
        for(values in valuesList){
            val newRowId = db.insertOrThrow(DatabaseContract.TimeTableEntry.TABLE_NAME,null,values)
            Log.d("debug","New Row Inserted with Id : $newRowId")
        }
        mDbHelper.close()
    }
    //Read all the rows in the database and log them
    fun read(context: Context){
        var mDbHelper = TimeTableDbHelper(context)
        var db = mDbHelper.readableDatabase
        var projection = arrayOf(DatabaseContract.TimeTableEntry.COLUMN_NAME_DAY,DatabaseContract.TimeTableEntry.COLUMN_NAME_START_TIME,
                DatabaseContract.TimeTableEntry.COLUMN_NAME_END_TIME,DatabaseContract.TimeTableEntry.COLUMN_NAME_TYPE,
                DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT,DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT_SHORT,
                DatabaseContract.TimeTableEntry.COLUMN_NAME_TEACHER,DatabaseContract.TimeTableEntry.COLUMN_NAME_LOCATION,DatabaseContract.TimeTableEntry.COLUMN_NAME_NOTES)
        var cursor = db.query(
                DatabaseContract.TimeTableEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )
        var rows = ArrayList<String>()
        while (cursor.moveToNext()){
            var day = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_DAY))
            var start_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_START_TIME))
            var end_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_END_TIME))
            var type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_TYPE))
            var subject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT))
            var subject_short = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT_SHORT))
            var teacher = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_TEACHER))
            var location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_LOCATION))
            var notes = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_NOTES))
            var row = "$day : $start_time : $end_time : $type : $subject : $subject_short : $teacher : $location : $notes"
            rows.add(row)
        }

        for(row in rows){
            Log.d("debug",row)
        }

        mDbHelper.close()
    }
}


object QueryHelper{

    //Get the list of classes for each day specified
    fun queryByDay(context: Context,day: String): ArrayList<Class>{
        var classes = ArrayList<Class>()


        var mDbHelper = TimeTableDbHelper(context)
        var db = mDbHelper.readableDatabase
        var projection = arrayOf(DatabaseContract.TimeTableEntry.COLUMN_NAME_DAY,DatabaseContract.TimeTableEntry.COLUMN_NAME_START_TIME,
                DatabaseContract.TimeTableEntry.COLUMN_NAME_END_TIME,DatabaseContract.TimeTableEntry.COLUMN_NAME_TYPE,
                DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT,DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT_SHORT,
                DatabaseContract.TimeTableEntry.COLUMN_NAME_TEACHER,DatabaseContract.TimeTableEntry.COLUMN_NAME_LOCATION,DatabaseContract.TimeTableEntry.COLUMN_NAME_NOTES)
        var selection = "${DatabaseContract.TimeTableEntry.COLUMN_NAME_DAY} = ?"
        var selectionArgs = arrayOf(day)
        var cursor = db.query(
                DatabaseContract.TimeTableEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )
        while (cursor.moveToNext()){
            var day = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_DAY))
            var start_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_START_TIME))
            var end_time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_END_TIME))
            var type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_TYPE))
            var subject = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT))
            var subject_short = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_SUBJECT_SHORT))
            var teacher = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_TEACHER))
            var location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_LOCATION))
            var notes = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TimeTableEntry.COLUMN_NAME_NOTES))
            var newClass = Class(day,start_time,end_time,type,subject,subject_short,teacher,location,notes)
            Log.d("debug",newClass.toString())
            classes.add(newClass)
        }
        mDbHelper.close()
        Log.d("debug","Hey")
        return classes
    }
}

