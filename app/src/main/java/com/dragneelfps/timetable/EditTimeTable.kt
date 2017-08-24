package com.dragneelfps.timetable

import android.support.v4.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_edit_time_table.*
import kotlinx.android.synthetic.main.activity_edit_time_table.view.*
import kotlin.jvm.javaClass

class EditTimeTable : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        var view = inflater!!.inflate(R.layout.activity_edit_time_table,container,false)
        (activity as AppCompatActivity).supportActionBar?.title = "Edit Time Table"
        view.fab.setOnClickListener{
            var ft = fragmentManager.beginTransaction()
            ft.replace(R.id.content_view,AddClass())
            ft.addToBackStack(null)
            ft.commit()
        }
        return view
    }
}



