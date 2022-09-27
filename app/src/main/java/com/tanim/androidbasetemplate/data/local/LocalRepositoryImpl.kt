package com.tanim.androidbasetemplate.data.local

import Resource
import android.content.Context
import com.tanim.androidbasetemplate.base.AppExecutors
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import com.tanim.androidbasetemplate.managers.DataManager

class LocalRepositoryImpl(
    private val context: Context,
    private val dataManager: DataManager,
    appExecutors: AppExecutors
) : LocalRepository {
    override suspend fun getPackages(): Resource<ResourceString, ResourceString> {
        TODO("Not yet implemented")
    }

    override suspend fun insert() {
        TODO("Not yet implemented")
    }
}