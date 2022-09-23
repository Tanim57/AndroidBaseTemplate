package com.tanim.androidbasetemplate.data.reporitory

import Resource
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun getPackages(): Flow<Resource<ResourceString,ResourceString>>
    suspend fun getPackages(user: Int): Flow<Resource<ResourceString, ResourceString>>
    suspend fun insert()
}