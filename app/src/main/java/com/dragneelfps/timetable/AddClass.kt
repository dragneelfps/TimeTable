package com.dragneelfps.timetable

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_class.*
import java.util.*
import android.app.TimePickerDialog
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_add_class_new.*
import kotlinx.android.synthetic.main.activity_add_class_new.view.*

class AddClass : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.activity_add_class_new,container,false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_36dp)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Class"
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug","here2")
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        Log.d("debug","here")
        inflater.inflate(R.menu.add_class_confim_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item != null){
            when(item.itemId){
                R.id.confim_action -> {
                    Snackbar.make(root_view,"Class added",Snackbar.LENGTH_SHORT).show()
                    return true
                }
                android.R.id.home -> {
                    fragmentManager.popBackStackImmediate()
                }
            }
        }
        return super.onOptionsItemSelected(item)

    }


}
