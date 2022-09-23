package com.tanim.androidbasetemplate.base

import androidx.databinding.ViewDataBinding
import com.tanim.androidbasetemplate.base.BaseViewModel
import javax.inject.Inject
import com.tanim.androidbasetemplate.base.BaseActivity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tanim.androidbasetemplate.App
import com.tanim.androidbasetemplate.di.component.DaggerFragmentComponent
import com.tanim.androidbasetemplate.di.component.FragmentComponent
import com.tanim.androidbasetemplate.di.module.FragmentModule

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment(),
    BaseContract.View {
    // the root view
    var rootView: View? = null

    @Inject
    protected lateinit var mViewModel: V

    var baseActivity: BaseActivity<*, *>? = null

    lateinit var viewDataBinding: T

    private var mProgressDialog: ProgressDialog? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    abstract fun bindView()

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection(buildComponent)
        super.onCreate(savedInstanceState)
        bindView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layout, container, false)
        rootView = viewDataBinding.getRoot()
        return rootView
    }

    override fun onResume() {
        super.onResume()
    }

    val isFragmentAvailable: Boolean
        get() = activity != null && view != null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            baseActivity = context
            baseActivity!!.onFragmentAttached()
        }
    }

    private val buildComponent: FragmentComponent
        private get() = DaggerFragmentComponent.builder()
            .appComponent((requireContext().applicationContext as App).appComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    abstract fun performDependencyInjection(buildComponent: FragmentComponent?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(bindingVariable, mViewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

    override val isNetworkConnected: Boolean
        get() = baseActivity!!.isNetworkConnected

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onDestroy() {
        if (baseActivity != null) {
            tag?.let { baseActivity!!.onFragmentDetached(it) }
        }
        super.onDestroy()
    }

    abstract val layout: Int
    val isActivityFinishing: Boolean
        get() = requireActivity().isFinishing

    override fun showProgressbar() {
        hideProgressbar()
        if (this.activity != null && !this.activity!!.isFinishing) {
            mProgressDialog = ViewUtils.showLoadingDialog(this.context)
        }
    }

    override fun onPause() {
        hideProgressDialog()
        super.onPause()
    }

    override fun onDestroyView() {
        hideProgressDialog()
        super.onDestroyView()
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
        ViewUtils.enableDisableViewGroup(rootView as ViewGroup, true)
    }

    override fun hideKeyBoard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyBoard()
        }
    }

    override fun disableAllView() {
        ViewUtils.enableDisableViewGroup(rootView as ViewGroup, false)
    }

    override fun showSnackBar(stringId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showSnackBar(stringId)
        }
    }

    override fun showSnackBarError(stringId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showSnackBarError(stringId)
        }
    }

    override fun showSnackBar(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showSnackBar(message)
        }
    }

    override fun showSnackBarError(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showSnackBarError(message)
        }
    }

    override fun hideSnackBar() {
        if (baseActivity != null) {
            baseActivity!!.hideSnackBar()
        }
    }

    override fun showShortToast(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showShortToast(message)
        }
    }

    override fun showLongToast(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showLongToast(message)
        }
    }

    override fun showShortToast(stringId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showShortToast(stringId)
        }
    }

    override fun showLongToast(stringId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showLongToast(stringId)
        }
    }

    override fun finishView() {
         requireActivity().finish()
    }

    override fun hasPermission(permission: String): Boolean {
        return if (baseActivity != null) {
            baseActivity!!.hasPermission(permission)
        } else false
    }

    override fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (baseActivity != null) {
            baseActivity!!.requestPermissionsSafely(permissions, requestCode)
        }
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
        if (baseActivity != null) {
            baseActivity!!.showError(message)
        }
    }

    override fun showErrorInDetail(message: String, detail: String) {
        if (baseActivity != null) {
            baseActivity!!.showErrorInDetail(message, detail)
        }
    }

    override fun hideError() {}
    interface FragmentCallback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}