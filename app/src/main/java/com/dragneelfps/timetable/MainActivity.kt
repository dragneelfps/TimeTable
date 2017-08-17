package com.dragneelfps.timetable

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.Toolbar
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    /*
    *   Current Main Activity
    *   Opens up the set TimeTable view
     */

    lateinit var classes: ArrayList<Class>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*
        *   To create and read Sample database of TimeTable
         */
        SampleCreater.create(applicationContext)   //Creates new random database for the TimeTable
        //SampleCreater.read(applicationContext)

        var timeTableAdapter = TimeTablePagerAdapter(applicationContext,supportFragmentManager)
        pager.adapter = timeTableAdapter

        tabs.setupWithViewPager(pager)


    }

    override fun onBackPressed() {
        if(fragmentManager.backStackEntryCount > 0) {
            Log.d("debug","fragment back")
            fragmentManager.popBackStack()
        }
        else{
            Log.d("debug","default back")
            super.onBackPressed()
        }

    }
}

class TimeTablePagerAdapter(var context: Context,var fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    /*
    *   Adapter for the  main timetable screen. Loads up fragment for each day
     */
    var days = arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday")
    var dayFragments: ArrayList<SampleFragment> = ArrayList()
    /*
    * Each fragment for a day is created only once and then stored in the class variable for repeated use in getItem
     */
    init {
        for(day in days){
            dayFragments.add(SampleFragment.newInstance(context,day))
        }
    }


    override fun getItem(position: Int): Fragment {
        var dayFragment = dayFragments[position]
        return dayFragment
    }

    override fun getCount(): Int {
        return days.size
    }


    override fun getPageTitle(position: Int): CharSequence {
        return days[position]
    }
}



