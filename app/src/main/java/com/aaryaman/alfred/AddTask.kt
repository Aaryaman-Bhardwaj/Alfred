package com.aaryaman.alfred

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aaryaman.alfred.db.DbManager
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        add_task.setOnClickListener {
            val dbManager= DbManager(this)

            val values= ContentValues()
            values.put("Name", edit_task_name.text.toString())
            values.put("Time", edit_task_time.text.toString())
            values.put("Snooze", edit_task_snooze.text.toString())
            values.put("Today", 0)

            val ID= dbManager.InsertRegularTask(values)

            if (ID>0)
                Toast.makeText(this, "Task added ✅ \n Id- $ID", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Error occurred ", Toast.LENGTH_SHORT).show()
        }

    }
}