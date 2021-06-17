package com.aaryaman.alfred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaryaman.alfred.models.ListBaseString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_weekly_log.*

class WeeklyLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_log)


//        button.setOnClickListener {
//            Toast.makeText(this, "ggggg", Toast.LENGTH_SHORT).show()
//            recycler_date.apply {
//                layoutManager = LinearLayoutManager(this@WeeklyLog, LinearLayoutManager.HORIZONTAL, false)
////                addItemDecoration(RvDecor(30))
//                adapter = dateRecyclerViewAdapter()
//            }
//            recycler_task_name.apply {
//                layoutManager = LinearLayoutManager(this@WeeklyLog)
////                addItemDecoration(RvDecor(30))
//                adapter = taskNameRecyclerViewAdapter()
//            }
//            recycler_tick.apply {
//                layoutManager = GridLayoutManager(this@WeeklyLog, dateList.size)
////                addItemDecoration(RvDecor(30))
//                adapter = tickRecyclerViewAdapter()
//            }
//
//        }

    }
}