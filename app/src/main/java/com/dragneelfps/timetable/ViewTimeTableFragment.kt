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
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_time_table.*
import kotlinx.android.synthetic.main.activity_view_time_table.view.*
import kotlin.collections.ArrayList

class ViewTimeTableFragment : Fragment() {
    /*
    *   Current Main Activity
    *   Opens up the set TimeTable view
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.activity_view_time_table,container,false)
        view.pager.adapter = TimeTablePagerAdapter(context,fragmentManager)
        (activity as AppCompatActivity).supportActionBar?.title = "Time Table"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_reorder_black_24dp)
        view.tabs.setupWithViewPager(view.pager)


        return view
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item != null) {
            when (item.itemId){
                android.R.id.home ->{
                    Log.d("debug","HereADAWDAWD")
                    activity.drawer_layout.openDrawer(GravityCompat.START)
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
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



