package com.tanim.androidbasetemplate.core.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager
import timber.log.Timber

class HomeViewModel(dataManager: DataManager,
                    repository: DataRepository
) :BaseViewModel(dataManager, repository) {


}