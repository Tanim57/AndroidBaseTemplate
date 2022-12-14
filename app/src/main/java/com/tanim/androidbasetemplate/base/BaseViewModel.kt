
package com.tanim.androidbasetemplate.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.managers.DataManager
import java.lang.ref.WeakReference

open class BaseViewModel/*<T : BaseContract.View?>*/(
    val dataManager: DataManager,val repository: DataRepository
) : ViewModel()/*, BaseContract.ViewModel<T>*/ {

    var isLoading = ObservableBoolean()

    //private var view: WeakReference<T>? = null

    var toastMessage = SingleLiveEvent<ResourceString>()
    var errorMessage = SingleLiveEvent<ResourceString>()

    override fun onCleared() {
        super.onCleared()
    }


/*
    override fun bindView(view: T) {
        //if (this.view == null)
        this.view = WeakReference(view)
    }

    override fun unBind() {
        view = null
    }

    override fun getView(): T? {
        return if (view != null) view!!.get() else null
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }*/
}