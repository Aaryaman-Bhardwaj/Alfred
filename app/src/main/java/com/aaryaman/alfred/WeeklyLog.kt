package com.aaryaman.alfred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaryaman.alfred.models.ListBaseString
import com.aaryaman.alfred.recycler.dateRecyclerViewAdapter
import com.aaryaman.alfred.recycler.taskNameRecyclerViewAdapter
import com.aaryaman.alfred.recycler.tickRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_weekly_log.*

class WeeklyLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_log)


        dateList = arrayListOf(ListBaseString("May-17"), ListBaseString("May-18"), ListBaseString("May-19"), ListBaseString("May-20"))
        tickList = arrayListOf(ListBaseString("0"), ListBaseString("1"), ListBaseString("1"), ListBaseString("0"), ListBaseString("1"), ListBaseString("1"), ListBaseString("1"), ListBaseString("1"), ListBaseString("1"), ListBaseString("1"), ListBaseString("1"), ListBaseString("0"), ListBaseString("1"), ListBaseString("0"), ListBaseString("1"), ListBaseString("0"), ListBaseString("1"), ListBaseString("1"), ListBaseString("1"),  ListBaseString("1"))
        nameList = arrayListOf(ListBaseString("Jogging"), ListBaseString("Reading"), ListBaseString("Cleaning"), ListBaseString("Purchase"), ListBaseString("Medicine"))



        button.setOnClickListener {
            Toast.makeText(this, "ggggg", Toast.LENGTH_SHORT).show()
            recycler_date.apply {
                layoutManager = LinearLayoutManager(this@WeeklyLog, LinearLayoutManager.HORIZONTAL, false)
//                addItemDecoration(RvDecor(30))
                adapter = dateRecyclerViewAdapter()
            }
            recycler_task_name.apply {
                layoutManager = LinearLayoutManager(this@WeeklyLog)
//                addItemDecoration(RvDecor(30))
                adapter = taskNameRecyclerViewAdapter()
            }
            recycler_tick.apply {
                layoutManager = GridLayoutManager(this@WeeklyLog, dateList.size)
//                addItemDecoration(RvDecor(30))
                adapter = tickRecyclerViewAdapter()
            }

        }

    }
}