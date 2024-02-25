package com.example.myapplication.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DialogDate {
    fun showCalendar(context: Context, tv: TextView){
        val cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(tv,cal)
            }

        (DatePickerDialog(
            context, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, cal.get(Calendar.YEAR), cal.get(
                Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
                )
    }
    @SuppressLint("SimpleDateFormat", "ResourceAsColor")
    private fun updateDateInView(view: TextView, cal: Calendar) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat)
        view.text = sdf.format(cal.time).toUpperCase(Locale.ROOT)
    }


    @SuppressLint("SimpleDateFormat")
    fun showWatch(context: Context, textView: TextView){
        val cal = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
            cal.set(Calendar.MINUTE, minute)
            val format = "HH:mm"
            val sdf = SimpleDateFormat(format)
            textView.text = sdf.format(cal.time)
        }
        TimePickerDialog(context,timeListener,cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
            true).show()


    }

}