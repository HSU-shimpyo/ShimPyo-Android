package com.example.shimpyoo.settings.breathing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R

class BreathingFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.activity_breathing2, container, false)
        val thirdFragment = BreathingFragment3()

        // 레이아웃 설정 후에 breathingBtn 찾기
        val breathingBtn = view.findViewById<ImageView>(R.id.breathingBtn)
        breathingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, thirdFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
