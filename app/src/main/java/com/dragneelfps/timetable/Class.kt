package com.dragneelfps.timetable

/**
 * Created by srawa on 8/16/2017.
 */
class Class(var day: String,var startTime: String, var endTime: String, var type: String, var subject: String, var subjectShort: String,
            var teacher: String){
    override fun toString(): String {
        return "Class(day='$day', startTime='$startTime', endTime='$endTime', type='$type', subject='$subject', subjectShort='$subjectShort', teacher='$teacher')"
    }
}