package com.dragneelfps.timetable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_class.*
import java.util.*
import android.app.TimePickerDialog
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_class_new.*

class AddClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class_new)

        setSupportActionBar(appbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Class"




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_class_confim_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item != null){
            when(item.itemId){
                R.id.confim_action -> {
                    Snackbar.make(root_view,"Class added",Snackbar.LENGTH_SHORT).show()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
