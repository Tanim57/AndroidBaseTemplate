package com.tanim.androidbasetemplate.base

import androidx.core.util.Supplier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Singleton

@Singleton
class ViewModelFactory<T : ViewModel>(
    private val viewModelClass: Class<T>,
    private val viewSupplier: Supplier<T>
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(viewModelClass)) {
            viewSupplier.get() as T
        } else {
            throw IllegalArgumentException("Unknown Class name " + viewModelClass.name)
        }
    }
}