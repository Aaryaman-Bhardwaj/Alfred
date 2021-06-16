package com.aaryaman.alfred

import android.app.*
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaryaman.alfred.db.DbManager
import com.aaryaman.alfred.models.ListBaseString
import com.aaryaman.alfred.models.Task
import com.aaryaman.alfred.recycler.cont
import com.aaryaman.alfred.recycler.homeRecyclerViewAdapter
import com.aaryaman.alfred.recycler.specialTaskHomeRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Locale
import kotlin.collections.ArrayList


var dateList: ArrayList<ListBaseString> = ArrayList()
var tickList: ArrayList<ListBaseString> = ArrayList()
var nameList: ArrayList<ListBaseString> = ArrayList()
var todaysTaskList : ArrayList<Task> = ArrayList()
var todaysSpecialTaskList : ArrayList<Task> = ArrayList()
var todaysTimeList : ArrayList<String> = ArrayList()

const val millisInADay = 86400000
const val millisIn530hrs = 23400000

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        dateList = arrayListOf(ListBaseString("May-17"))//, ListBaseString("May-18"), ListBaseString("May-19"), ListBaseString("May-20"))
        tickList = arrayListOf(ListBaseString("0"), ListBaseString("0"), ListBaseString("1"), ListBaseString("0"), ListBaseString("1"))
        nameList = arrayListOf(ListBaseString("Jogging"), ListBaseString("Reading"), ListBaseString("Cleaning"), ListBaseString("Purchase"), ListBaseString("Medicine"))





            DbManager(this)
            refreshSpecialList()
            refreshList()
            triggerNotification()




        goto_reg_task.setOnClickListener {
            startActivity(Intent(this, RegularTasks::class.java))
        }
        goto_special_task.setOnClickListener {
            startActivity(Intent(this, AddSpecialTask::class.java))
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

        home_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//                addItemDecoration(RvDecor(30))
            adapter = homeRecyclerViewAdapter()
        }

    }

    private fun LoadQuerySpecialToday(): Cursor {
        val dbManager = DbManager(this)
        return dbManager.QuerySpecialToday()
    }

    fun refreshSpecialList(){

        cont = this
        val cursor= LoadQuerySpecialToday()

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

        todaysSpecialTaskList = ArrayList()
        todaysTimeList = ArrayList()

        for( i in 0 until itemNames.size) run {
            val name = itemNames[i]
            val tick = itemTodays[i]
            val time = itemTimes[i]
            val a= Task(name, tick, time)
            todaysSpecialTaskList.add(a)
            todaysTimeList.add(itemTimes[i])

        }

//        Toast.makeText(this, "ggggg", Toast.LENGTH_SHORT).show()

        special_task_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//                addItemDecoration(RvDecor(30))
            adapter = specialTaskHomeRecyclerViewAdapter()
        }

    }

//    fun addSpecialTask(time: String){
//        val dbManager= DbManager(this)
//
//        val values= ContentValues()
//        values.put("Name", edit_add_special_task.text.toString())
//        values.put("Time", time)
//        values.put("Snooze", "30")
//        values.put("Today", 0)
//
//        val ID= dbManager.InsertSpecialTask(values)
//
//        if (ID>0)
//            Toast.makeText(this, "Task added ✅ \n Id- $ID", Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(this, "Error occurred ", Toast.LENGTH_SHORT).show()
//    }


    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "RegularTaskChannel"
            val description = "Channel for notifying Regular Tasks"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("regularTask", name, importance)

            channel.description = description

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun triggerNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                Toast.makeText(this, "reminder set", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ReminderBroadcast::class.java)

                val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

                for (i in 0 until todaysTimeList.size)
                    if (todaysTimeList.size > 0) {
//                        Toast.makeText(this, "reminder set aaggaaa", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", todaysTimeList.toString())
                        val date = Date(todaysTimeList[i]).toString() //Mon May 31 10:42:19 GMT+05:30 2021
                        Log.e("TAG", Date(todaysTimeList[i]).toString())

                        val formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
                        val localDate = LocalDateTime.parse(date, formatter)
                        var timeInMilliseconds = localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
                        timeInMilliseconds += millisIn530hrs
                        Log.e("TAG", "Date in milli :: FOR API >= 26 >>> $timeInMilliseconds")
                        todaysTimeList[i] = timeInMilliseconds.toString()

                    }

            if (todaysTimeList.size > 0) {
                todaysTimeList.sort()
                alarmManager.set(AlarmManager.RTC_WAKEUP, todaysTimeList[0].toLong(), pendingIntent)
            }

        }

    }

    override fun onResume() {
        super.onResume()
        refreshSpecialList()
        refreshList()
        triggerNotification()
    }



}