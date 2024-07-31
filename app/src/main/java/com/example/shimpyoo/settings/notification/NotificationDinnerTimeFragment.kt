package com.example.shimpyoo.settings.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R

class NotificationDinnerTimeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.notification_dinner_time_view, container, false)
        val completeFragment = NotificationCompleteFragment()

        // 레이아웃 설정 후에 settingButton 찾기
        val settingBtn = view.findViewById<Button>(R.id.nextButton)
        settingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, completeFragment)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}