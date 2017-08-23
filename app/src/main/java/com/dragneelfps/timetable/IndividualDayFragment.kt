package com.dragneelfps.timetable

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.day_fragment.view.*

/**
 * Created by srawa on 8/14/2017.
 */
class IndividualDayFragment : Fragment() {

    lateinit var classes: ArrayList<Class>
    lateinit var dayFragmentAdapter : DayFragmentAdapter


    companion object {
        fun newInstance(context: Context,day: String): IndividualDayFragment {
            Log.d("debug","Fragment created for $day")
            var fg = IndividualDayFragment()
            fg.classes = QueryHelper.queryByDay(context,day)    //query is done only when the fragment is created and then stored as class variable
            fg.dayFragmentAdapter = DayFragmentAdapter(context,fg.classes)  //adapter is created and stored
            return fg
        }
    }

    //creating the UI for the fragment and adding the adapter to its ListView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.day_fragment,container,false)
        view.list.adapter = dayFragmentAdapter
        return view
    }



    override fun onPause() {
        super.onPause()
    }
}