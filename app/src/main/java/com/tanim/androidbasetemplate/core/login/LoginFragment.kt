package com.tanim.androidbasetemplate.core.login

import com.tanim.androidbasetemplate.BR
import com.tanim.androidbasetemplate.R
import com.tanim.androidbasetemplate.base.BaseFragment
import com.tanim.androidbasetemplate.databinding.FragnentLoginBinding
import com.tanim.androidbasetemplate.di.component.FragmentComponent

class LoginFragment : BaseFragment<FragnentLoginBinding, LoginViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel


    override fun performDependencyInjection(buildComponent: FragmentComponent) {
        buildComponent.inject(this)
    }

    override val layout: Int
        get() = R.layout.fragnent_login
}