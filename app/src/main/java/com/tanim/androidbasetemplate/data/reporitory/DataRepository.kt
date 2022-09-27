package com.tanim.androidbasetemplate.data.reporitory

import Resource
import com.tanim.androidbasetemplate.data.auth.LoginResponse
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun login(userName:String,password:String): Flow<Resource<LoginResponse,ResourceString>>

}