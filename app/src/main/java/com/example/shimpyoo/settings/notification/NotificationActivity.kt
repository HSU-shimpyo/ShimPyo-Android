package com.example.shimpyoo.settings.notification

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.MainActivity
import com.example.shimpyoo.R

class NotificationActivity : AppCompatActivity(), OnFragmentInteractionListener {

    private lateinit var settingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificationsetting)

        val mainFragment = NotificationMainFragment()

        // 메인 프래그먼트 붙이기
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mainFragment)
            .commit()

        val backBtn: ImageButton = findViewById(R.id.arrowLeftButton)
        backBtn.setOnClickListener {
            finish() // 이전 화면으로 돌아가기
        }
    }
}
