package com.dragneelfps.timetable

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by srawa on 8/14/2017.
 */
class TimeTableDbHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "TimeTable.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseContract.TimeTableEntry.SQL_CREATE_ENTRIES) //Execute only if db is not null
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}