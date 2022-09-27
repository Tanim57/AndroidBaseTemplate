package com.tanim.androidbasetemplate.extension

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.tanim.androidbasetemplate.base.BaseFragment

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

fun BaseFragment<*, *>.close() = fragmentManager?.popBackStack()

val BaseFragment<*, *>.appContext: Context get() = activity?.applicationContext!!
