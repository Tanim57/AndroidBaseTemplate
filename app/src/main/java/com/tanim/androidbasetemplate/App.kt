package com.tanim.androidbasetemplate

import android.app.Application
import android.content.Context
import com.tanim.androidbasetemplate.App
import com.tanim.androidbasetemplate.di.component.AppComponent
import timber.log.Timber
import com.tanim.androidbasetemplate.logging.AppDebugTree
import com.tanim.androidbasetemplate.di.component.DaggerAppComponent
import androidx.multidex.MultiDex
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.BuildConfig

class App : Application() {
    private val TAG = App::class.java.simpleName
    var appComponent: AppComponent? = null
    override fun onCreate() {
        super.onCreate()
        context = this@App
        if (BuildConfig.DEBUG) {
            Timber.plant(AppDebugTree())
        }
        appComponent = DaggerAppComponent.builder().application(this).build()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        var context: Context? = null
            private set
    }
}