package com.aaryaman.alfred

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val builder = NotificationCompat.Builder(context!!, "regularTask")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Reminder")
//            .setContentIntent()
            .setContentText("Doing the things?")
            .setPriority(NotificationCompat.PRIORITY_LOW)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(200, builder.build())


    }
}