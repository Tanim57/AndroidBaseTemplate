package com.tanim.androidbasetemplate.extension

import com.tanim.androidbasetemplate.data.mapper.Status

fun Status.isLoading() = this == Status.LOADING

fun Status.error() = this == Status.ERROR

fun Status.success() = this == Status.SUCCESS
