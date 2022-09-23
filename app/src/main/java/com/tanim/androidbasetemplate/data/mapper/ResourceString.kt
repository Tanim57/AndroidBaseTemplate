package com.tanim.androidbasetemplate.data.mapper

import android.content.Context
import androidx.annotation.Keep

@Keep
sealed class ResourceString{
    abstract fun format(context: Context): String
}
class IdResourceString(val id: Int): ResourceString() {
    override fun format(context: Context): String {
        return context.getString(id)
    }
}
class TextResourceString(val text: String): ResourceString() {
    override fun format(context: Context): String {
        return text
    }
}
class FormatResourceString(val id: Int, val values: Array<Any>): ResourceString() {
    override fun format(context: Context): String {
        return context.getString(id, *values)
    }
}
