package com.tanim.androidbasetemplate.base

import android.annotation.TargetApi
import android.content.Context
import android.os.Build

interface BaseContract {
    interface ViewModel<T : View?> {
        fun bindView(view: T)
        fun unBind()
        fun getView(): T?
        fun logOut()
    }

    interface View {

        @TargetApi(Build.VERSION_CODES.M)
        fun hasPermission(permission: String): Boolean
        @TargetApi(Build.VERSION_CODES.M)
        fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int)
        val isNetworkConnected: Boolean
        fun showProgressbar()
        fun showProgressbar(message: String)
        fun hideProgressbar()
        fun showProgressDialog()
        fun showProgressDialog(message: String)
        fun hideProgressDialog()
        fun hideSnackBar()
        fun showShortToast(message: String)
        fun showShortToast(resourceId: Int)
        fun showLongToast(message: String)
        fun showLongToast(resourceId: Int)
        fun showSnackBar(message: String)
        fun showSnackBar(resourceId: Int)
        fun showSnackBarError(message: String)
        fun showSnackBarError(resourceId: Int)
        fun showSwipeRefreshLayout()
        fun hideSwipeRefreshLayout()
        fun finishView()
        fun hideKeyBoard()
        fun showError(message: String)
        fun showErrorInDetail(message: String, detail: String)
        fun hideError()
        fun enableAllView()
        fun disableAllView()
    }
}