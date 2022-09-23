package com.tanim.androidbasetemplate.data.local


import Resource
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getPackages(): Resource<ResourceString,ResourceString>
    suspend fun insert()
}