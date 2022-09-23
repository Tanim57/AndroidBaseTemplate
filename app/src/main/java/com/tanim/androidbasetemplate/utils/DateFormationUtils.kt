package com.tanim.androidbasetemplate.utils

import androidx.databinding.BindingAdapter
import android.widget.TextView
import com.tanim.androidbasetemplate.utils.DateFormationUtils
import android.text.TextUtils
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormationUtils {
    @BindingAdapter(value = ["date", "format"])
    fun setDate(textView: TextView, date: Date?, dateFormat: String?) {
        textView.text = getStringFromDate(date, dateFormat, textView.textLocale)
    }

    fun getStringFromDate(date: Date?, dateFormat: String?, locale: Locale?): String? {
        val parseFormat = SimpleDateFormat(dateFormat, locale)
        return try {
            parseFormat.format(date)
        } catch (e: Exception) {
            null
        }
    }

    fun changeDateFormat(
        source: String?,
        sourceFormat: String?,
        targetFormat: String?,
        locale: Locale?
    ): String? {
        val date = getDateFromString(source, sourceFormat) ?: return null
        return getStringFromDate(date, targetFormat, locale)
    }

    fun getStringFromDate(date: Date?, dateFormat: String?): String? {
        val parseFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return try {
            parseFormat.format(date)
        } catch (e: Exception) {
            null
        }
    }

    fun getDateFromString(date: String?, dateFormat: String?): Date? {
        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(dateFormat)) {
            return null
        }
        val parseFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return try {
            parseFormat.parse(date)
        } catch (ex: ParseException) {
            null
        }
    }

    fun changeDateFormat(source: String?, sourceFormat: String?, targetFormat: String?): String? {
        return if (source == null || sourceFormat == null) {
            null
        } else try {
            val date = getDateFromString(source, sourceFormat) ?: return null
            getStringFromDate(date, targetFormat)
        } catch (e: Exception) {
            null
        }
    }

    fun getDifferenceOfTwoTime(arrDate: Long, depDate: Long): Long {
        var diff: Long = 0
        diff = if (arrDate <= depDate) {
            arrDate + 24 * 60 * 60 * 1000 - depDate
        } else {
            arrDate - depDate
        }
        return diff
    }

    fun getDlDateFormat(date: String?): String {
        if (date == null) {
            return DateFormat.DATE_FORMAT_yyyyMMdd
        }
        return if (date.length == 8) {
            DateFormat.DATE_FORMAT_yyyyMMdd
        } else DateFormat.DATE_FORMAT_yyMMdd
    }

    fun getDifferenceOfTwoTime(arrDate: Date, depDate: Date): Long {
        var diff: Long = 0
        diff = if (arrDate.time <= depDate.time) {
            arrDate.time + 24 * 60 * 60 * 1000 - depDate.time
        } else {
            arrDate.time - depDate.time
        }
        return diff
    }

    fun getDifferenceOfTwoTime(arrTime: String?, depTime: String?): Long {
        val depDate = getDateFromString(depTime, "HH:mm")
        val arrDate = getDateFromString(arrTime, "HH:mm")
        var diff: Long = 0
        diff = if (arrDate!!.time <= depDate!!.time) {
            arrDate.time + 24 * 60 * 60 * 1000 - depDate.time
        } else {
            arrDate.time - depDate.time
        }
        return diff
    }

    fun getPreviousDate(day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1 * day)
        return calendar.time
    }

    fun getFutureDate(day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, day)
        return calendar.time
    } //    public List<DayOfWeek> getDaysOfWeekFromLocale(){
    //
    //        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
    //        DayOfWeek[] daysOfWeek = DayOfWeek.values();
    //
    //        if(firstDayOfWeek!=DayOfWeek.MONDAY){
    //            DayOfWeek[] rhs = Arrays.copyOfRange(daysOfWeek,firstDayOfWeek.ordinal(),daysOfWeek[daysOfWeek.length-1].ordinal());
    //            DayOfWeek[] lhs = Arrays.copyOfRange(daysOfWeek,0,firstDayOfWeek.ordinal());
    //
    //            daysOfWeek = rhs + lhs;
    //        }
    //
    //
    //    }
}