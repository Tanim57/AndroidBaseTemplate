package com.tanim.androidbasetemplate.di.component

import com.tanim.androidbasetemplate.di.scope.FragmentScope
import com.tanim.androidbasetemplate.di.module.FragmentModule
import com.tanim.androidbasetemplate.di.component.AppComponent
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [AppComponent::class])
interface FragmentComponent 