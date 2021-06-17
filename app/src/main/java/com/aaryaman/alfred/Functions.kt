package com.aaryaman.alfred

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aaryaman.alfred.recycler.contMainActivity
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

fun triggerNotifications(){}

open class Functions: AppCompatActivity() {

    fun triggerNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//            val intent = Intent(contMainActivity, ReminderBroadcast("", "")::class.java)

            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

            for (i in 0 until todaysTimeList.size)
                if (todaysTimeList.size > 0) {
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


}

