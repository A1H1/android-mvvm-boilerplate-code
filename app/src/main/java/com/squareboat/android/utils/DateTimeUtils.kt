package com.squareboat.android.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

interface TimeDialogListener {
    fun onTimeSelected(hour: String, minute: String)
}

interface DateDialogListener {
    fun onDateSelected(year: String, month: String, day: String)
}

object DateTimeUtils {
    fun selectTime(context: Context, title: String, listener: TimeDialogListener) {
        val mCurrentTime: Calendar = Calendar.getInstance()
        val hour: Int = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute: Int = mCurrentTime.get(Calendar.MINUTE)
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                listener.onTimeSelected(
                    "${if (selectedHour.toString().length == 1) "0" else ""}$selectedHour",
                    "${if (selectedMinute.toString().length == 1) "0" else ""}$selectedMinute"
                )
            },
            hour,
            minute,
            false
        )

        mTimePicker.setTitle(title)
        mTimePicker.show()
    }

    fun selectDate(context: Context, listener: DateDialogListener) {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]

        val mMonth = c[Calendar.MONTH]

        val mDay = c[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, monthOfYear, dayOfMonth ->
                listener.onDateSelected(
                    year.toString(),
                    "${if ((monthOfYear + 1).toString().length == 1) "0" else ""}${monthOfYear + 1}",
                    "${if (dayOfMonth.toString().length == 1) "0" else ""}$dayOfMonth"
                )
            }, mYear, mMonth, mDay
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    fun getCurrentDateTime(format: String): String {
        val c = Calendar.getInstance().time

        val df = SimpleDateFormat(format, Locale.getDefault())
        return df.format(c)
    }

    fun formatDateTime(dateTime: String, originFormat: String, parserFormat: String): String {
        val formatter = SimpleDateFormat(originFormat, Locale.getDefault())
        val date: Date?
        return try {
            date = formatter.parse(dateTime)
            val dateFormat = SimpleDateFormat(parserFormat, Locale.getDefault())

            if (date == null) {
                ""
            } else {
                dateFormat.format(date)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }
}