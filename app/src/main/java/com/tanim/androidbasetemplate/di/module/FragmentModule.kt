package com.tanim.androidbasetemplate.di.module

import android.content.Context
import androidx.core.util.Supplier
import androidx.lifecycle.ViewModelProvider
import com.tanim.androidbasetemplate.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanim.androidbasetemplate.MainViewModel
import com.tanim.androidbasetemplate.base.ViewModelFactory
import com.tanim.androidbasetemplate.core.home.HomeViewModel
import com.tanim.androidbasetemplate.core.login.LoginViewModel
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment<*, *>) {
    @Provides
    fun provideContext(): Context {
        return fragment.requireContext()
    }

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(fragment.requireContext())
    }

    @Provides
    fun provideLoginViewModel(dataManager: DataManager,repository: DataRepository): LoginViewModel {

        val supplier: Supplier<LoginViewModel> =
            Supplier { LoginViewModel(dataManager,repository) }

        val factory: ViewModelFactory<LoginViewModel> =
            ViewModelFactory<LoginViewModel>(LoginViewModel::class.java, supplier)

        return ViewModelProvider(fragment, factory).get(LoginViewModel::class.java)
    }

    @Provides
    fun provideHomeViewModel(dataManager: DataManager,repository: DataRepository): HomeViewModel {

        val supplier: Supplier<HomeViewModel> =
            Supplier { HomeViewModel(dataManager,repository) }

        val factory: ViewModelFactory<HomeViewModel> =
            ViewModelFactory<HomeViewModel>(HomeViewModel::class.java, supplier)

        return ViewModelProvider(fragment, factory).get(HomeViewModel::class.java)
    }
}