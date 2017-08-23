package com.dragneelfps.timetable

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_view_time_table.*
import kotlin.collections.ArrayList

class ViewTimeTableActivity : AppCompatActivity() {
    /*
    *   Current Main Activity
    *   Opens up the set TimeTable view
     */

    lateinit var classes: ArrayList<Class>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_time_table)


        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_reorder_black_24dp)

        /*
        *   To create and read Sample database of TimeTable
         */
        SampleCreater.create(applicationContext)   //Creates new random database for the TimeTable
        //SampleCreater.read(applicationContext)

        var timeTableAdapter = TimeTablePagerAdapter(applicationContext,supportFragmentManager)
        pager.adapter = timeTableAdapter

        tabs.setupWithViewPager(pager)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item != null) {
            when (item.itemId) {
                R.id.home ->{
                    drawer_layout.openDrawer(GravityCompat.START)
                    return true
                }
                else -> return super.onOptionsItemSelected(item)
            }
        }
        else
            return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(isNavOpen())
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    fun isNavOpen(): Boolean{
        return drawer_layout.isDrawerOpen(GravityCompat.START)
    }
}

class TimeTablePagerAdapter(var context: Context,var fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    /*
    *   Adapter for the  main timetable screen. Loads up fragment for each day
     */
    var days = arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday")
    var dayFragments: ArrayList<IndividualDayFragment> = ArrayList()
    /*
    * Each fragment for a day is created only once and then stored in the class variable for repeated use in getItem
     */
    init {
        for(day in days){
            dayFragments.add(IndividualDayFragment.newInstance(context,day))
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



