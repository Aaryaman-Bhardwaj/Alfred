package com.aaryaman.alfred

import android.app.TimePickerDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.aaryaman.alfred.db.DbManager
import kotlinx.android.synthetic.main.activity_add_task.*
import java.text.SimpleDateFormat
import java.util.*

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        edit_task_time.setIs24HourView(true);

        val cal = Calendar.getInstance()
        edit_task_time.setOnTimeChangedListener  { _, hour, minute ->
            cal.set(Calendar.HOUR, hour)
            cal.set(Calendar.MINUTE, minute)
        }

        goto_home.setOnClickListener {
            super.onBackPressed()
        }

        add_task.setOnClickListener {
            val dbManager= DbManager(this)

            val values= ContentValues()
            values.put("Name", edit_task_name.text.toString())
            values.put("Time", SimpleDateFormat("HH:mm").format(cal.timeInMillis))
            values.put("Today", 0)

//            textView4.text = cal.timeInMillis.toString()
            val ID= dbManager.InsertRegularTask(values)

            if (    ID>0)
                Toast.makeText(this, "Task added ✅ \n Id- $ID", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Error occurred ", Toast.LENGTH_SHORT).show()

            edit_task_name.text= "".toEditable()
        }


    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

}