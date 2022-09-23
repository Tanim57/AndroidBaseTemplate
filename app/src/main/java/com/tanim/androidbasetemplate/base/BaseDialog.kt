package com.tanim.androidbasetemplate.base

import androidx.databinding.ViewDataBinding
import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.base.BaseActivity
import javax.inject.Inject
import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.tanim.androidbasetemplate.App
import com.tanim.androidbasetemplate.di.component.DaggerDialogComponent
import com.tanim.androidbasetemplate.di.component.DialogComponent
import com.tanim.androidbasetemplate.di.module.DialogModule

abstract class BaseDialog<T : ViewDataBinding, V : BaseViewModel<*>> : DialogFragment(),
    BaseContract.View {
    private var mActivity: BaseActivity<*, *>? = null

    @Inject
    protected lateinit var mViewModel: V
    private var mProgressDialog: ProgressDialog? = null
    var rootView: View? = null
    lateinit var viewDataBinding: T

    override fun hasPermission(permission: String): Boolean {
        return mActivity!!.hasPermission(permission)
    }

    override fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (mActivity != null) {
            mActivity!!.requestPermissionsSafely(permissions, requestCode)
        }
    }

    override val isNetworkConnected: Boolean
        get() = mActivity != null && mActivity!!.isNetworkConnected

    abstract val bindingVariable: Int
    abstract fun bindView()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //rootView = inflater.inflate(getLayout(), container, false);
        //mViewDataBinding = DataBindingUtil.findBinding(rootView);
        viewDataBinding = DataBindingUtil.inflate(inflater, layout, container, false)
        rootView = viewDataBinding.getRoot()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()
    }

    override fun onDestroy() {
        if (mActivity != null) {
            tag?.let { mActivity!!.onFragmentDetached(it) }
        }
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection(buildComponent)
        super.onCreate(savedInstanceState)
        bindView()
    }

    abstract val layout: Int
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val mActivity = context
            this.mActivity = mActivity
            mActivity.onFragmentAttached()
        }
    }

    private val buildComponent: DialogComponent
        private get() = DaggerDialogComponent.builder()
            .appComponent((requireContext().applicationContext as App).appComponent)
            .dialogModule(DialogModule(this))
            .build()

    abstract fun performDependencyInjection(buildComponent: DialogComponent?)

    //    @Override
    //    public void dismissDialog(String tag) {
    //        dismiss();
    //        if(mActivity!=null)
    //            mActivity.onFragmentDetached(tag);
    //    }
    override fun hideKeyBoard() {
        if (mActivity != null) mActivity!!.hideKeyBoard()
    }

    override fun showProgressbar() {
        hideProgressbar()
        if (this.activity != null && !this.activity!!.isFinishing) {
            mProgressDialog = ViewUtils.showLoadingDialog(this.context)
        }
    }

    override fun showProgressbar(message: String) {
        hideProgressbar()
        if (this.activity != null && !this.activity!!.isFinishing) {
            mProgressDialog = ViewUtils.showLoadingDialog(this.context, message)
        }
    }

    override fun hideProgressbar() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
            mProgressDialog!!.dismiss()
        }
    }

    override fun enableAllView() {
        if (mActivity != null) {
            mActivity!!.enableAllView()
        }
    }

    override fun disableAllView() {
        if (mActivity != null) {
            mActivity!!.disableAllView()
        }
    }

    override fun showSnackBar(stringId: Int) {
        if (mActivity != null) {
            mActivity!!.showSnackBar(stringId)
        }
    }

    override fun showSnackBarError(stringId: Int) {
        if (mActivity != null) {
            mActivity!!.showSnackBarError(stringId)
        }
    }

    override fun showSnackBar(message: String) {
        if (mActivity != null) {
            mActivity!!.showSnackBar(message)
        }
    }

    override fun showSnackBarError(message: String) {
        if (mActivity != null) {
            mActivity!!.showSnackBarError(message)
        }
    }

    override fun hideSnackBar() {
        if (mActivity != null) {
            mActivity!!.hideSnackBar()
        }
    }

    override fun showShortToast(message: String) {
        if (mActivity != null) {
            mActivity!!.showShortToast(message)
        }
    }

    override fun showLongToast(message: String) {
        if (mActivity != null) {
            mActivity!!.showLongToast(message)
        }
    }

    override fun showShortToast(stringId: Int) {
        if (mActivity != null) {
            mActivity!!.showShortToast(stringId)
        }
    }

    override fun showLongToast(stringId: Int) {
        if (mActivity != null) {
            mActivity!!.showLongToast(stringId)
        }
    }

    override fun finishView() {
        requireActivity().finish()
    }

    override fun showProgressDialog() {
        hideProgressDialog()
        if (this.activity != null && !this.activity!!.isFinishing) {
            mProgressDialog = ViewUtils.showLoadingDialog(this.context)
        }
    }

    override fun showProgressDialog(message: String) {
        hideProgressDialog()
        if (this.activity != null && !this.activity!!.isFinishing) {
            mProgressDialog = ViewUtils.showLoadingDialog(this.context, message)
        }
    }

    override fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
            mProgressDialog!!.dismiss()
        }
    }

    override fun showSwipeRefreshLayout() {}
    override fun hideSwipeRefreshLayout() {}
    override fun showError(message: String) {
        if (mActivity != null) {
            mActivity!!.showError(message)
        }
    }

    override fun showErrorInDetail(message: String, detail: String) {
        if (mActivity != null) {
            mActivity!!.showErrorInDetail(message, detail)
        }
    }

    override fun hideError() {}
}