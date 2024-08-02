package com.example.shimpyoo.settings.breathing

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R
import com.example.shimpyoo.settings.notification.NotificationLunchTimeFragment

class BreathingFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.activity_breathing1, container, false)
        val secondFragment = BreathingFragment2()

        // 레이아웃 설정 후에 breathingBtn 찾기
        val breathingBtn = view.findViewById<ImageView>(R.id.breathingBtn)
        breathingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, secondFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
