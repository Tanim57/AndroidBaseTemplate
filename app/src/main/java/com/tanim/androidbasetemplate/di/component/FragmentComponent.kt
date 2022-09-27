package com.tanim.androidbasetemplate.di.component

import com.tanim.androidbasetemplate.core.home.HomeFragment
import com.tanim.androidbasetemplate.core.login.LoginFragment
import com.tanim.androidbasetemplate.di.module.FragmentModule
import com.tanim.androidbasetemplate.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [AppComponent::class])
interface FragmentComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(homeFragment: HomeFragment)
} 