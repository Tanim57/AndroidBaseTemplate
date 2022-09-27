package com.tanim.androidbasetemplate

import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager

class MainViewModel(dataManager: DataManager, repository: DataRepository) :
    BaseViewModel(dataManager, repository),MainContract.ViewModel {
}