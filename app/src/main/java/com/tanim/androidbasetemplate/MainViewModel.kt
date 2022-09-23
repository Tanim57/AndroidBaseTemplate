package com.tanim.androidbasetemplate

import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.managers.DataManager

class MainViewModel(dataManager: DataManager) :
    BaseViewModel<MainContract.View>(dataManager),MainContract.ViewModel {

}