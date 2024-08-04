package com.example.shimpyoo.settings.hospital

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.shimpyoo.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

class HospitalScheduleMainFragment : Fragment() {

    private lateinit var calendarView: MaterialCalendarView
    private val TAG = "HospitalScheduleMainFragment"
    private lateinit var selectedDates: List<CalendarDay>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트에 레이아웃을 붙임
        val view = inflater.inflate(R.layout.hospital_calendar, container, false)
        val completeButton: Button = view.findViewById(R.id.saveButton)

        // 캘린더 뷰 초기화
        calendarView = view.findViewById(R.id.calendarview)

        completeButton.setOnClickListener {
            if (::selectedDates.isInitialized && selectedDates.isNotEmpty()) {
                showSaveDialog()
            } else {
                Toast.makeText(context, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 월, 요일을 한글로 보이게 설정 (MonthArrayTitleFormatter의 작동을 확인하려면 밑의 setTitleFormatter()를 지운다)
        calendarView.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        // 좌우 화살표 사이 연, 월의 폰트 스타일 설정
        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader)

        // 요일 선택 시 내가 정의한 드로어블이 적용되도록 함
        calendarView.setOnRangeSelectedListener(object : OnRangeSelectedListener {
            override fun onRangeSelected(widget: MaterialCalendarView, dates: List<CalendarDay>) {
                selectedDates = dates
                val startDay = dates[0].date.toString()
                val endDay = dates[dates.size - 1].date.toString()
                Log.e(TAG, "시작일 : $startDay, 종료일 : $endDay")
            }
        })

        // 일자 선택 시 내가 정의한 드로어블이 적용되도록 한다
        calendarView.addDecorators(DayDecorator(requireContext()))

        // 좌우 화살표 가운데의 연/월이 보이는 방식 커스텀
        calendarView.setTitleFormatter(object : TitleFormatter {
            override fun format(day: CalendarDay): CharSequence {
                val inputText = day.date
                val calendarHeaderElements = inputText.toString().split("-")
                return "${calendarHeaderElements[0]}년 ${calendarHeaderElements[1]}월"
            }
        })
    }

    private fun showSaveDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.hospital_reservation_time_setting_view, null)
        val customTitleView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_title, null)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCustomTitle(customTitleView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // 다이얼로그 크기 조정
        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(window?.attributes)
        layoutParams.width = 1000
        layoutParams.height = 1500
        window?.setBackgroundDrawable(ColorDrawable(0xCC444444.toInt()))
    }


    private fun saveSelectedDates() {
        // 여기에서 선택된 날짜 범위를 저장하는 로직을 구현합니다.
        if (::selectedDates.isInitialized) {
            val startDay = selectedDates[0].date.toString()
            val endDay = selectedDates[selectedDates.size - 1].date.toString()
            Log.e(TAG, "저장된 시작일 : $startDay, 저장된 종료일 : $endDay")
            Toast.makeText(context, "날짜 범위가 저장되었습니다: $startDay - $endDay", Toast.LENGTH_SHORT).show()
        }
    }

    /* 선택된 요일의 background를 설정하는 Decorator 클래스 */
    private class DayDecorator(context: Context) : DayViewDecorator {

        private val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector)!!

        override fun shouldDecorate(day: CalendarDay): Boolean {
            return true
        }

        override fun decorate(view: DayViewFacade) {
            view.setSelectionDrawable(drawable)
        }
    }
}
