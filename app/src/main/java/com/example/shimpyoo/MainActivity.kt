package com.example.shimpyoo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.settings.notification.NotificationActivity
import com.example.shimpyoo.settings.notification.NotificationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        val notificationSettingBtn: ImageView = findViewById(R.id.medicineSettingButton)
        notificationSettingBtn.setOnClickListener {
            val intent = Intent(applicationContext, NotificationActivity::class.java)
            startActivity(intent)
        }
    }
}
