package com.dragneelfps.timetable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_class.*
import java.util.*
import android.app.TimePickerDialog
import android.text.Editable
import android.text.SpannableStringBuilder

class AddClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)

        start_time_input.setOnClickListener { view ->
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)
            var timePicker = TimePickerDialog(AddClass@this,TimePickerDialog.OnTimeSetListener { timePicker, hr , min ->
                val text = hr.toString() + ":" + min.toString()
                start_time_input.text = SpannableStringBuilder(text)
            },hour,minute,true)
            timePicker.setTitle("Select Start Time")
            timePicker.show()
        }

    }
}
