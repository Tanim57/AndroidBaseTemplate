package com.tanim.androidbasetemplate.core.login

import Resource
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.data.auth.LoginResponse
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import com.tanim.androidbasetemplate.data.mapper.Status
import com.tanim.androidbasetemplate.data.mapper.TextResourceString
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(dataManager: DataManager,
                     repository: DataRepository
) :BaseViewModel(dataManager, repository) {

    val userName = ObservableField<String>()
    val password = ObservableField<String>()

    private val _loginResponse = MutableLiveData<Resource<LoginResponse,ResourceString>>()

    val loginResponse : LiveData<Resource<LoginResponse,ResourceString>>
        get() = _loginResponse;

    fun login(){
        if(userName.get() == null){
            errorMessage.value = TextResourceString("User name can't be empty")
            return
        }

        if(password.get() == null ){
            errorMessage.value = TextResourceString("Password can't be empty")
            return
        }

        if(_loginResponse.value?.status?.equals(Status.LOADING) == true){
            return
        }

        _loginResponse.value = Resource.loading()

        viewModelScope.launch {
            repository.login(userName.get().toString(), password.get().toString()).collect{
                _loginResponse.value = it
            }
        }

    }
}