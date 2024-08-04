package com.example.shimpyoo.settings.weeklycalendar

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shimpyoo.R
import com.example.shimpyoo.databinding.FragmentWeeklyCalendarBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

class WeeklyCalendarFragment : Fragment() {

    private var _binding: FragmentWeeklyCalendarBinding? = null
    private val binding get() = _binding!!

    lateinit var calendarAdapter: WeeklyCalendarAdapter
    private var calendarList = ArrayList<WeeklyCalendarVO>()

    companion object {
        fun newInstance() = WeeklyCalendarFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeeklyCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val week_day: Array<String> = resources.getStringArray(R.array.calendar_day)

        calendarAdapter = WeeklyCalendarAdapter(calendarList)

        calendarList.apply {
            val dateFormat = DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko"))
            val monthFormat = DateTimeFormatter.ofPattern("MM월 dd일").withLocale(Locale.forLanguageTag("ko"))

            val localDate = LocalDateTime.now().format(monthFormat)
            binding.textYearMonth.text = localDate

            // 현재 날짜 기준으로 주간 시작일 계산
            val today = LocalDate.now()
            val startOfWeek = today.with(DayOfWeek.SUNDAY)

            for (i in 0..6) {
                val date = startOfWeek.plusDays(i.toLong()).format(dateFormat)
                val day = week_day[i]
                calendarList.add(WeeklyCalendarVO(date, day))
            }
        }

        binding.weekRecycler.adapter = calendarAdapter
        binding.weekRecycler.layoutManager = GridLayoutManager(context, 7)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}