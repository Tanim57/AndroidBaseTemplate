package com.tanim.androidbasetemplate

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.tanim.androidbasetemplate.base.BaseActivity
import com.tanim.androidbasetemplate.databinding.ActivityMainBinding
import com.tanim.androidbasetemplate.di.component.ActivityComponent

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainContract.View {


    private lateinit var navController: NavController

    override val bindingVariable
        get() = BR.viewModel

    override val layout: Int
        get() = R.layout.activity_main


    override fun performDependencyInjection(buildComponent: ActivityComponent) {
        buildComponent.inject(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment

        navController = navHostFragment.navController



    }
}