package com.example.shimpyoo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.settings.breathing.BreathingActivity
import com.example.shimpyoo.settings.breathing.BreathingActivity2
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

        val hospitalSettingButton: ImageView = findViewById(R.id.hospitalSettingButton)
        hospitalSettingButton.setOnClickListener {
            val intent = Intent(this, HospitalScheduleActivity::class.java)
            startActivity(intent)
        }

        val breathingBtn: ImageView = findViewById(R.id.breathingBtn)
        breathingBtn.setOnClickListener {
            val intent = Intent(this, BreathingActivity::class.java)
            startActivity(intent)
        }
    }
}
