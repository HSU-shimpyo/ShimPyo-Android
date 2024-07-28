package com.example.shimpyoo.settings.notification

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R

class NotificationFragment : Fragment() {

    private var listner : OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentInteractionListener) {
            listner = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.notification_setting_view, container, false)

        val before: TextView = view.findViewById(R.id.before)
        val after: TextView = view.findViewById(R.id.after)
        val now : TextView = view.findViewById(R.id.now)
        val half : TextView = view.findViewById(R.id.half)
        val onehours : TextView = view.findViewById(R.id.onehours)
        val twohours : TextView = view.findViewById(R.id.twohours)

        listner?.onFragmentInteraction("다음")

        // 기본 상태 설정
        setInitialState(before)
        setInitialState(after)
        /*
        setInitialState(now)
        setInitialState(half)
        setInitialState(onehours)
        setInitialState(twohours)
        */

        before.setOnClickListener {
            updateSelection(before, after)
        }
        after.setOnClickListener {
            updateSelection(after, before)
        }

        return view
    }

    private fun setInitialState(textView: TextView) {
        val drawable = textView.background as GradientDrawable
        drawable.setColor(ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
        drawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.notSelectedTextColor))
    }

    private fun updateSelection(selected: TextView, unselected: TextView) {
        val selectedDrawable = selected.background as GradientDrawable
        selectedDrawable.setColor(ContextCompat.getColor(requireContext(), R.color.selectedBoxColor))
        selectedDrawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.originalBorderColor))
        selected.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextColor))

        val unselectedDrawable = unselected.background as GradientDrawable
        unselectedDrawable.setColor(ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
        unselectedDrawable.setStroke(2, ContextCompat.getColor(requireContext(), R.color.notSelectedBoxColor))
        unselected.setTextColor(ContextCompat.getColor(requireContext(), R.color.notSelectedTextColor))
    }
}
