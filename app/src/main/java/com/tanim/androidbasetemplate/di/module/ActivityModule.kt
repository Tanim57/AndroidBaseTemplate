package com.tanim.androidbasetemplate.di.module

import androidx.core.util.Supplier
import com.tanim.androidbasetemplate.base.BaseActivity
import com.tanim.androidbasetemplate.base.ViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.tanim.androidbasetemplate.MainViewModel
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {
    @Provides
    fun provideMainViewModel(dataManager: DataManager,repository: DataRepository): MainViewModel {

        val supplier: Supplier<MainViewModel> =
            Supplier { MainViewModel(dataManager,repository) }

        val factory: ViewModelFactory<MainViewModel> =
            ViewModelFactory<MainViewModel>(MainViewModel::class.java, supplier)

        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }
}