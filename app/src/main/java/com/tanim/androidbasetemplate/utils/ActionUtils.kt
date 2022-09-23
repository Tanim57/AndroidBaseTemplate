package com.tanim.androidbasetemplate.utils

import android.content.Intent
import android.content.ActivityNotFoundException
import android.app.Activity
import android.content.Context
import android.net.Uri

object ActionUtils {
    fun openLink(context: Context, link: String?) {
        try {
            val rate = Intent(Intent.ACTION_VIEW)
            rate.data = Uri.parse(link)
            context.startActivity(rate)
        } catch (anfe: ActivityNotFoundException) {
            anfe.fillInStackTrace()
        }
    }

    fun startNewActivityLeft2Right(activity: Activity, className: Class<*>?) {
        val intent = Intent(activity.applicationContext, className)
        activity.startActivity(intent)
    }

    fun exitApp(context: Context) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    } //    public static void restart(Context context) {
    //        ProcessPhoenix.triggerRebirth(context);
    //    }
    //
    //    public static void restart(Context context, Intent nextIntent) {
    //        ProcessPhoenix.triggerRebirth(context,nextIntent);
    //    }
}