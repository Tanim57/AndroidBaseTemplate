package com.tanim.androidbasetemplate

import com.tanim.androidbasetemplate.base.BaseActivity
import com.tanim.androidbasetemplate.databinding.ActivityMainBinding
import com.tanim.androidbasetemplate.di.component.ActivityComponent

open class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainContract.View {

    override val bindingVariable
        get() = BR.viewModel

    override val layout: Int
        get() = R.layout.activity_main

    override fun bindView() {
        mViewModel?.bindView(this)
    }

    override fun performDependencyInjection(buildComponent: ActivityComponent) {
        buildComponent.inject(this);
    }
}