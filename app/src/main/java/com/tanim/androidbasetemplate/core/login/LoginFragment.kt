package com.tanim.androidbasetemplate.core.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.tanim.androidbasetemplate.BR
import com.tanim.androidbasetemplate.BuildConfig
import com.tanim.androidbasetemplate.R
import com.tanim.androidbasetemplate.base.BaseFragment
import com.tanim.androidbasetemplate.data.mapper.Status
import com.tanim.androidbasetemplate.databinding.FragnentLoginBinding
import com.tanim.androidbasetemplate.di.component.FragmentComponent
import com.tanim.androidbasetemplate.extension.*

class LoginFragment : BaseFragment<FragnentLoginBinding, LoginViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel


    override fun performDependencyInjection(buildComponent: FragmentComponent) {
        buildComponent.inject(this)
    }

    override val layout: Int
        get() = R.layout.fragnent_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            if(it.status.isLoading()){
                viewDataBinding.btnLogin.invisible()
                viewDataBinding.loginProgress.visible()
            }else{
                viewDataBinding.btnLogin.visible()
                viewDataBinding.loginProgress.invisible()
            }

            if(it.status.success()){
                NavHostFragment.findNavController(this).navigate(R.id.action_navigate_home);
            }else if(it.status.error()){
                it.message?.let { it1 -> showShortToast(it1.format(requireContext())) }
            }
        })

        mViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            showShortToast(it.format(requireContext()))
        })
        mViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            showSnackBar(it.format(requireContext()))
        })

        if(BuildConfig.DEBUG){
            viewDataBinding.etUserName.setText("test")
            viewDataBinding.etPassword.setText("test")
        }

    }
}