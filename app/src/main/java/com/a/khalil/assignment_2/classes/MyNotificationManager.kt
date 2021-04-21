package com.a.khalil.assignment_2.classes

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.a.khalil.assignment_2.R

class MyNotificationManager(var context: Context) {

    private val ID_NOTIFICATION = 2000
    private val CHANNEL_ID = "channel id"

    fun showSmallNotification(id: Int, title: String, message: String, intent: Intent?) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.setShowBadge(true)
            channel.description = message
            channel.enableLights(true) // لو كان الجوال مطفي بيضوي لحاله لما يجي الإشعار
            channel.enableVibration(true)// بيخلي الجوال يعمل هزاز

            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            ID_NOTIFICATION,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val notification: Notification = builder
            .setSmallIcon(R.drawable.ic_person)
            .setContentIntent(pendingIntent)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(id, notification)
    }

}