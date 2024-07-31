package com.example.shimpyoo.settings.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R

class NotificationLunchTimeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.notification_lunch_time_view, container, false)
        val dinnerTimeFragment = NotificationDinnerTimeFragment()
        val settingBtn = view.findViewById<Button>(R.id.nextButton)
        settingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, dinnerTimeFragment)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}