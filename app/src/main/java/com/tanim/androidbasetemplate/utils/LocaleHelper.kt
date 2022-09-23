package com.tanim.androidbasetemplate.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import java.util.*


//fun onAttach(context: Context, defaultLanguage: String): Context {
//    return setLocale(context, defaultLanguage)!!
//}

// the method is used to set the language at runtime
fun setLocale(context: Context?, language: String?): Context? {
    //persist(context, language)

    // updating the language for devices above android nougat
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        updateResources(context!!, language!!)
    } else {
        updateResourcesLegacy(context!!, language!!)
    }
    // for devices having lower version of android os
}


@TargetApi(Build.VERSION_CODES.N)
fun updateResources(context: Context, language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val configuration = context.getResources().getConfiguration()
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)

    return context.createConfigurationContext(configuration)
}

fun updateResourcesLegacy(context: Context, language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val resources = context.getResources()

    val configuration = resources.getConfiguration()
    configuration.locale = locale
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        configuration.setLayoutDirection(locale)
    }
    resources.updateConfiguration(configuration, resources.getDisplayMetrics())
    return context
}

fun getNoLocalContext(context: Context, language: String): Context {

    val locale = Locale(language)
    Locale.setDefault(locale)

    val resources = context.getResources()

    val configuration = resources.getConfiguration()
    configuration.locale = locale
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        configuration.setLayoutDirection(locale)
    }
    return context
}