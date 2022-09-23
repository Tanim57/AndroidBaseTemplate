package com.tanim.androidbasetemplate.base

import android.view.View

interface BaseInterface {
    interface OnItemViewListener<T> {
        fun onClick(view: View?, t: T) {}
        fun onLongClick(view: View?, t: T) {}
        fun onTouch(view: View?, t: T) {}
        fun onCheck(view: View?, t: T) {}
        fun onSelect(view: View?, t: T) {}
        fun onUpdate(view: View?, t: T) {}
        fun onClick(view: View?, t: T, vararg objects: Any?) {}
    }
}