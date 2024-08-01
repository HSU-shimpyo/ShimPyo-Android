package com.example.shimpyoo.settings.breathing

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.shimpyoo.R

class BreathingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breathing)

        val closeButton: ImageButton = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            finish()
        }
    }
}