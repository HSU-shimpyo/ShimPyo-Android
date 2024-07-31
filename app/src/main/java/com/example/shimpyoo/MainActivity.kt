package com.example.shimpyoo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.settings.hospital.HospitalScheduleActivity
import com.example.shimpyoo.settings.notification.NotificationActivity

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

        val hospitalSettingBtn: ImageView = findViewById(R.id.hospitalSettingButton)
        hospitalSettingBtn.setOnClickListener {
            val intent = Intent(applicationContext, HospitalScheduleActivity::class.java)
            startActivity(intent)
        }
    }
}
