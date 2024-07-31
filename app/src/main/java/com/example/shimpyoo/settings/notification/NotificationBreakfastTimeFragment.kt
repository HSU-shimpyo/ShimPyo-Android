package com.example.shimpyoo.settings.notification

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        val hours: EditText = view.findViewById(R.id.hours)
        val minutes: EditText = view.findViewById(R.id.minutes)
        val lunchTimeFragment = NotificationLunchTimeFragment()

        setInitialState(hours)
        setInitialState(minutes)

        hours.setOnFocusChangeListener { _, hasFocus ->
            updateSelection(hours, hasFocus)
        }

        minutes.setOnFocusChangeListener { _, hasFocus ->
            updateSelection(minutes, hasFocus)
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
        // editText.setBackgroundResource(R.drawable.edittext_background)  // XML에서 이미 설정되어 있음
        editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.Font_04_Gray))
    }

    private fun updateSelection(editText: EditText, isSelected: Boolean) {
        val stateListDrawable = editText.background as StateListDrawable

        val drawable: Drawable? = stateListDrawable.current
        if (drawable is GradientDrawable) {
            if (isSelected) {
                drawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.mainBlue))
                editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.Font_02_black))
            } else {
                drawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
                editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.Font_04_Gray))
            }
        }
    }
}
