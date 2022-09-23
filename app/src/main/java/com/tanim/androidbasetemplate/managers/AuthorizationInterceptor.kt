package com.tanim.androidbasetemplate.managers

import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.Throws

/**
 * Created by Tanim on 5/5/2020.
 */
@Singleton
class AuthorizationInterceptor (
private val session: Session) : Interceptor
{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainResponse = chain.proceed(chain.request())
        val mainRequest = chain.request()
        if (session.isLoggedIn()) {
            // if response code is 401 or 403, 'mainRequest' has encountered authentication error
            if (mainResponse.code() == 401 || mainResponse.code() == 403) {
                session.logOut()
            }
        } else {
            if (!(mainResponse.code() == 200 || mainResponse.code() == 201)) session.logOut()
        }
        return mainResponse
    }
}