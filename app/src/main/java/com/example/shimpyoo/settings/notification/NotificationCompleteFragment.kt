package com.example.shimpyoo.settings.notification

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.shimpyoo.MainActivity
import com.example.shimpyoo.R

class NotificationCompleteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.notification_complete_view, container, false)
        val goHomeBtn : Button = view.findViewById(R.id.goHomeButton)

        goHomeBtn.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}
