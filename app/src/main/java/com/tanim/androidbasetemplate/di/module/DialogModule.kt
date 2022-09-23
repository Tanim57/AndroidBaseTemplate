package com.tanim.androidbasetemplate.di.module

import android.content.Context
import com.tanim.androidbasetemplate.base.BaseDialog
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

@Module
class DialogModule(private val dialog: BaseDialog<*, *>) {
    @Provides
    fun provideContext(): Context {
        return dialog.requireContext()
    }

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(dialog.requireContext())
    }
}