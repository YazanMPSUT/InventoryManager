package com.example.inventorymanager

//import AfterNotification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notification: AppCompatActivity() {
    private var notificationId1 :Int = 123
    private var notificationId2 :Int = 234
    private var notificationId3 :Int = 345
    private val channelId = "App_Channel.testNotification"
    private val description = "Please check inventory items!"
    private fun basicNotification() {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Inventory Notification")
            .setContentText("Please check inventory items!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        val name = "test_notification"
        val descriptionText = description
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


}