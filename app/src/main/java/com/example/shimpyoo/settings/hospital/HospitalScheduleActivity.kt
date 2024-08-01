package com.example.shimpyoo.settings.hospital

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.R
import com.example.shimpyoo.settings.notification.NotificationMainFragment

class HospitalScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitalsetting)

        val closeButton: ImageView = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            finish() // 이전 화면으로 돌아가기
        }

        val mainFragment = HospitalScheduleMainFragment()

        // 메인 프래그먼트 붙이기
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, mainFragment)
            .commit()
    }
}
