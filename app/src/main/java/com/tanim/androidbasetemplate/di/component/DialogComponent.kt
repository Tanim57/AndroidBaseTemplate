package com.tanim.androidbasetemplate.di.component

import com.tanim.androidbasetemplate.di.scope.DialogScope
import com.tanim.androidbasetemplate.di.module.DialogModule
import com.tanim.androidbasetemplate.di.component.AppComponent
import dagger.Component

@DialogScope
@Component(modules = [DialogModule::class], dependencies = [AppComponent::class])
interface DialogComponent 