package com.example.shimpyoo.settings.weeklycalendar

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.shimpyoo.R
import com.example.shimpyoo.databinding.WeeklyCalendarItemListBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class WeeklyCalendarAdapter(private val cList: List<WeeklyCalendarVO>) :
    RecyclerView.Adapter<WeeklyCalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(private val binding: WeeklyCalendarItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: WeeklyCalendarVO) {
            binding.date.text = item.cl_date
            binding.day.text = item.cl_day

            val today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko")))

            // 오늘 날짜와 캘린더의 오늘 날짜가 같을 경우 background_white 적용하기
            if (item.cl_date == today) {
                binding.weekCardview.setBackgroundResource(R.drawable.background_white)
                binding.day.setTextColor(binding.root.context.getColor(R.color.Font_02_black))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = WeeklyCalendarItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(cList[position])
    }

    override fun getItemCount(): Int {
        return cList.size
    }
}
