package com.dragneelfps.timetable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_view_time_table.*

/**
 * Created by srawa on 8/23/2017.
 */
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_reorder_black_24dp)

        /*
        *   To create and read Sample database of TimeTable
         */
        SampleCreater.create(applicationContext)   //Creates new random database for the TimeTable
        //SampleCreater.read(applicationContext)
    }
}