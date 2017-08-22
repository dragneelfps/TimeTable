package com.dragneelfps.timetable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.day_list_item.view.*

/**
 * Created by srawa on 8/16/2017.
 */
class DayFragmentAdapter : BaseAdapter{
    /*
    *   adapter for each day . Fills up the day with the classes held on the day
     */
    var mContext: Context
    var mClasses: ArrayList<Class>
    var mInflater: LayoutInflater
    constructor(context: Context,classes: ArrayList<Class>){
        mContext = context
        mClasses = classes
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getView(postion: Int, convertView: View?, viewGroup: ViewGroup?): View {
        var view = mInflater.inflate(R.layout.day_list_item,viewGroup,false)
        view.subject.text = mClasses.get(postion).subjectShort
        view.subject_full.text = mClasses.get(postion).subject
        view.type.text = mClasses.get(postion).type
        view.start_timing.text = mClasses.get(postion).startTime
        view.end_timing.text = mClasses.get(postion).endTime
        view.location.text = mClasses.get(postion).location
        view.teacher.text = mClasses.get(postion).teacher
        view.notes.text = mClasses.get(postion).notes

        view.subject_full.visibility = View.GONE
        view.teacher.visibility = View.GONE
        view.notes.visibility = View.GONE
        view.setOnClickListener { view ->
            if(view.subject_full.isShown){
                view.subject_full.visibility = View.GONE
                view.teacher.visibility = View.GONE
                view.notes.visibility = View.GONE
                view.layoutParams = AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,150)
            }else{
                view.subject_full.visibility = View.VISIBLE
                view.teacher.visibility = View.VISIBLE
                view.notes.visibility = View.VISIBLE
                view.layoutParams = AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,250)
            }
        }
        return view
    }

    override fun getItem(p0: Int): Any =   mClasses.get(p0)

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = mClasses.size
}