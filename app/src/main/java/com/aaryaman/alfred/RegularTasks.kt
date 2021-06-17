package com.aaryaman.alfred

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaryaman.alfred.db.DbManager
import com.aaryaman.alfred.models.Task
import com.aaryaman.alfred.recycler.contMainActivity
import com.aaryaman.alfred.recycler.regularTaskListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_regular_tasks.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


lateinit var cont2: Context

fun showDialog(cont: Context, name: String) {
    lateinit var dialog: AlertDialog

    val builder = AlertDialog.Builder(cont)
    builder.setTitle("Delete the daily Task?")
//    builder.setMessage("Are you sure you want to delete this task?")

    val dialogClickListener = DialogInterface.OnClickListener{ _, which ->
        when(which){
            DialogInterface.BUTTON_POSITIVE -> DbManager(cont2).deleteRegularTask(name)
//            refreshList
        }
    }


    builder.setPositiveButton("YES", dialogClickListener)

    builder.setNegativeButton("NO", dialogClickListener)

    dialog = builder.create()
    dialog.show()

}

class RegularTasks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regular_tasks)

        cont2 =this
        refreshList()


        goto_home.setOnClickListener {
            super.onBackPressed()
        }

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddTask::class.java))
        }

    }

    fun refreshList() {

        contMainActivity = this
        val cursor= LoadQueryToday()

        val itemNames = mutableListOf<String>()
        val itemTimes = mutableListOf<String>()
//        val itemSnoozes = mutableListOf<String>()
        val itemTodays = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val itemName = getString(getColumnIndexOrThrow("Name"))
                val itemTime = getString(getColumnIndexOrThrow("Time"))
//                val itemSnooze = getString(getColumnIndexOrThrow("Snooze"))
                val itemToday = getString(getColumnIndexOrThrow("Today"))
                itemNames.add(itemName)
                itemTimes.add(itemTime)
//                itemSnoozes.add(itemSnooze)
                itemTodays.add(itemToday)
            }
        }

        todaysTaskList = ArrayList()
        todaysTimeList = ArrayList()


        for( i in 0 until itemNames.size) run {
            val name = itemNames[i]
            val tick = itemTodays[i]
            val time = itemTimes[i]
            val a= Task(name, tick, time)
            todaysTaskList.add(a)
            todaysTimeList.add(itemTimes[i])
        }

        for (i in 0 until todaysTimeList.size){
            val cal = Calendar.getInstance()
            val date = SimpleDateFormat("yyyy/MM/dd").format(cal.time)
            val time = date + " " +todaysTimeList[i]
            todaysTimeList[i] = time
        }


//        Toast.makeText(this, "ggggg", Toast.LENGTH_SHORT).show()

        regular_task_list.apply {
            layoutManager = LinearLayoutManager(this@RegularTasks)
//                addItemDecoration(RvDecor(30))
            adapter = regularTaskListAdapter()
        }

    }




    private fun LoadQueryToday(): Cursor {
        val dbManager = DbManager(this)
        return dbManager.QueryToday()
    }

    override fun onResume() {
        super.onResume()
        regular_task_list.apply {
            layoutManager = LinearLayoutManager(this@RegularTasks)
//                addItemDecoration(RvDecor(30))
            adapter = regularTaskListAdapter()
        }
    }


}