package com.aaryaman.alfred

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaryaman.alfred.db.DbManager
import com.aaryaman.alfred.models.ListBaseString
import com.aaryaman.alfred.models.Task
import com.aaryaman.alfred.recycler.cont
import com.aaryaman.alfred.recycler.homeRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_main.*

var dateList: ArrayList<ListBaseString> = ArrayList()
var tickList: ArrayList<ListBaseString> = ArrayList()
var nameList: ArrayList<ListBaseString> = ArrayList()
var todaysTaskList : ArrayList<Task> = ArrayList()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateList = arrayListOf(ListBaseString("May-17"))//, ListBaseString("May-18"), ListBaseString("May-19"), ListBaseString("May-20"))
        tickList = arrayListOf(ListBaseString("0"), ListBaseString("0"), ListBaseString("1"), ListBaseString("0"), ListBaseString("1"))
        nameList = arrayListOf(ListBaseString("Jogging"), ListBaseString("Reading"), ListBaseString("Cleaning"), ListBaseString("Purchase"), ListBaseString("Medicine"))



        button.setOnClickListener {
            refreshList()
        }

        button2.setOnClickListener {
            startActivity(Intent(this, WeeklyLog::class.java))
        }

        add_regular_task.setOnClickListener {
            startActivity(Intent(this, AddTask::class.java))
        }
    }

    private fun LoadQueryToday(): Cursor {
        val dbManager = DbManager(this)
        return dbManager.QueryToday()
    }

    fun refreshList(){

        cont = this
        val cursor= LoadQueryToday()

        val itemNames = mutableListOf<String>()
        val itemTimes = mutableListOf<String>()
        val itemSnoozes = mutableListOf<String>()
        val itemTodays = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val itemName = getString(getColumnIndexOrThrow("Name"))
                val itemTime = getString(getColumnIndexOrThrow("Time"))
                val itemSnooze = getString(getColumnIndexOrThrow("Snooze"))
                val itemToday = getString(getColumnIndexOrThrow("Today"))
                itemNames.add(itemName)
                itemTimes.add(itemTime)
                itemSnoozes.add(itemSnooze)
                itemTodays.add(itemToday)
            }
        }

        todaysTaskList = ArrayList()

        for( i in 0 until itemNames.size) run {
            val name = itemNames[i]
            val tick = itemTodays[i]
            val a= Task(name, tick)
            todaysTaskList.add(a)
        }

        Toast.makeText(this, "ggggg", Toast.LENGTH_SHORT).show()

        home_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//                addItemDecoration(RvDecor(30))
            adapter = homeRecyclerViewAdapter()
        }

    }


}