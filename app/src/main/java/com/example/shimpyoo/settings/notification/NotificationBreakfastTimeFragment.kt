package com.example.shimpyoo.settings.notification

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R

class NotificationBreakfastTimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.notification_breakfast_time_view, container, false)

        val hours : EditText = view.findViewById(R.id.hours)
        val minutes : EditText = view.findViewById(R.id.minutes)
        val lunchTimeFragment = NotificationLunchTimeFragment()

        setInitialState(hours)
        setInitialState(minutes)
        hours.setOnClickListener{
            updateSelection(hours,minutes)
        }
        minutes.setOnClickListener{
            updateSelection(hours,minutes)
        }

        // 레이아웃 설정 후에 settingButton 찾기
        val settingBtn = view.findViewById<Button>(R.id.nextButton)
        settingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, lunchTimeFragment)
                .addToBackStack(null)
                .commit()
       }
        return view
    }

    private fun setInitialState(editText: EditText) {
        val drawable = editText.background as GradientDrawable
        drawable.setColor(ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
        drawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
        editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.Font_04_Gray))
    }

    private fun updateSelection(hours: EditText, minutes: EditText) {
        val hoursDrawable = hours.background as GradientDrawable
        hoursDrawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.mainBlue))
        hours.setTextColor(ContextCompat.getColor(requireContext(), R.color.Font_02_black))

        val minutesDrawable = minutes.background as GradientDrawable
        minutesDrawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.mainBlue))
        minutes.setTextColor(ContextCompat.getColor(requireContext(), R.color.Font_02_black))
    }
}
