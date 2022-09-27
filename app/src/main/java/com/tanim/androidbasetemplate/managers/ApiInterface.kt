package com.tanim.androidbasetemplate.managers

import com.tanim.androidbasetemplate.data.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("userName") userName:String, @Field("password") password:String):
            Response<LoginResponse>
    
}