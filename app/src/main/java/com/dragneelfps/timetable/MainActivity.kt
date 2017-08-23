package com.dragneelfps.timetable

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_time_table.*

/**
 * Created by srawa on 8/23/2017.
 */
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        *   To create and read Sample database of TimeTable
         */
        SampleCreater.create(applicationContext)   //Creates new random database for the TimeTable
        //SampleCreater.read(applicationContext)


        var ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.content_view, ViewTimeTableFragment())
        ft.commit()

        nav.setNavigationItemSelectedListener{ item: MenuItem ->
            Log.d("debug","Navigation Item selected")
            item.setChecked(true)


            if(item.itemId == R.id.time_table_item){
                Log.d("debug","Time TAble selected")
                var ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view,ViewTimeTableFragment())
                ft.commit()
                supportActionBar?.title = "Time Table"
            }else if(item.itemId == R.id.edit_time_table_item){
                Log.d("debug","Edit selected")
                var ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view,EditTimeTable())
                ft.commit()
                supportActionBar?.title = "Edit Time Table"
            }
            drawer_layout.closeDrawers()
            true
        }

        setSupportActionBar(appbar)
        supportActionBar?.title = "Time Table"

    }


}