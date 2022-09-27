
package com.tanim.androidbasetemplate.data.mapper

import Resource
import com.tanim.androidbasetemplate.data.auth.LoginResponse
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun login(userName:String,password:String): Resource<LoginResponse,ResourceString>
}