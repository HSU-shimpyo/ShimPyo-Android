package com.example.shimpyoo.settings.hospital

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.R

class HospitalScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_calendar)

        val closeButton: ImageView = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            finish() // 이전 화면으로 돌아가기
        }
    }
}
