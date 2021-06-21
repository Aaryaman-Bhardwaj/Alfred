package com.aaryaman.alfred

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderBroadcast: BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {

        val builder = NotificationCompat.Builder(context!!, "regularTask")
            .setSmallIcon(R.drawable.logo2)
            .setContentTitle(notificationTitle)
//            .setContentIntent()
            .setContentText("Doing the things?")
            .setPriority(NotificationCompat.PRIORITY_LOW)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(200, builder.build())


//        triggerNextNotification()

    }

//    fun triggerNextNotification(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            val intent = Intent(contMainActivity, ReminderBroadcast("", "")::class.java)
//
//            val pendingIntent = PendingIntent.getBroadcast(contMainActivity, 0, intent, 0)
//
//            val alarmManager = getSystemService() as AlarmManager
//
//            for (i in 0 until todaysTimeList.size)
//                if (todaysTimeList.size > 0) {
//                    Log.e("TAG", todaysTimeList.toString())
//                    val date = Date(todaysTimeList[i]).toString() //Mon May 31 10:42:19 GMT+05:30 2021
//                    Log.e("TAG", Date(todaysTimeList[i]).toString())
//
//                    val formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
//                    val localDate = LocalDateTime.parse(date, formatter)
//                    var timeInMilliseconds = localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
//                    timeInMilliseconds += millisIn530hrs
//                    Log.e("TAG", "Date in milli :: FOR API >= 26 >>> $timeInMilliseconds")
//                    todaysTimeList[i] = timeInMilliseconds.toString()
//
//                }
//
//            if (todaysTimeList.size > 0) {
//                todaysTimeList.sort()
//                alarmManager.set(AlarmManager.RTC_WAKEUP, todaysTimeList[0].toLong(), pendingIntent)
//            }
//
//        }
//
//    }

}