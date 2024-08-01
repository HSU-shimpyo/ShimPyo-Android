package com.example.shimpyoo.settings.breathing

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.MainActivity
import com.example.shimpyoo.R

class BreathingActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breathing2)

        val breathingBtn: ImageView = findViewById(R.id.breathingBtn)
        breathingBtn.setOnClickListener {
            val intent = Intent(this, BreathingActivity3::class.java)
            startActivity(intent)
        }

        val closeButton: ImageButton = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}