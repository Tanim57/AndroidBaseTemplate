package com.tanim.androidbasetemplate.di.component

import com.tanim.androidbasetemplate.di.scope.ActivityScope
import com.tanim.androidbasetemplate.di.module.ActivityModule
import com.tanim.androidbasetemplate.di.component.AppComponent
import com.tanim.androidbasetemplate.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}