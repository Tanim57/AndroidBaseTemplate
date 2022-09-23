package com.tanim.androidbasetemplate.managers


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("favourite/{categoryId}")
    suspend fun getMarkedQuestions(
        @Path("categoryId") categoryId: Int?,
        @Query("page") page: Int?
    ): Response<Object>
    
}