
package com.tanim.androidbasetemplate.di.component

import android.app.Application
import com.tanim.androidbasetemplate.App
import com.tanim.androidbasetemplate.base.AppExecutors
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.di.module.AppModule
import com.tanim.androidbasetemplate.managers.DataManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)
    fun getDataManager(): DataManager
    fun getDataRepository(): DataRepository
    fun getAppExecutors(): AppExecutors

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}