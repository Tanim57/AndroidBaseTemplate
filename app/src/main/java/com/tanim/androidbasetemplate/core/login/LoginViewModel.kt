package com.tanim.androidbasetemplate.core.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager
import timber.log.Timber

class LoginViewModel(dataManager: DataManager,
                     repository: DataRepository
) :BaseViewModel(dataManager, repository) {

    val userName = ObservableField<String>()
    val password = ObservableField<String>()

    fun login(){
        Timber.d(userName.get())

    }
}