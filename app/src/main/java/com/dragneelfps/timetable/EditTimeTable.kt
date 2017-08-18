package com.dragneelfps.timetable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_time_table.*
import kotlin.jvm.javaClass

class EditTimeTable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_time_table)
        fab.setOnClickListener { view ->
            var intent = Intent(EditTimeTable@this,AddClass::class.java)
            startActivity(intent)
        }
    }
}



