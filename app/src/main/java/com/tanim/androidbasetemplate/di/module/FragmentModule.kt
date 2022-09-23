package com.tanim.androidbasetemplate.di.module

import android.content.Context
import com.tanim.androidbasetemplate.base.BaseFragment
import androidx.recyclerview.widget.LinearLayoutManager
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
}