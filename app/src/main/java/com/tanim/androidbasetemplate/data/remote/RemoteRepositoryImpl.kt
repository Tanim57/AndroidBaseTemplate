package com.tanim.androidbasetemplate.di.component
import Resource
import com.tanim.androidbasetemplate.BuildConfig
import com.tanim.androidbasetemplate.data.Constant
import com.tanim.androidbasetemplate.data.auth.LoginResponse
import com.tanim.androidbasetemplate.data.mapper.IdResourceString
import com.tanim.androidbasetemplate.data.mapper.RemoteRepository
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import com.tanim.androidbasetemplate.data.mapper.TextResourceString
import com.tanim.androidbasetemplate.managers.DataManager
import com.tanim.androidbasetemplate.utils.ErrorUtils
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RemoteRepositoryImpl (private val dataManager: DataManager) : RemoteRepository {



    private inline fun <reified T:Any> mapResponse(response: Any?): Resource<T,ResourceString> {
        try {
            return when (response) {
                is T -> {
                    Resource.success(response)
                }
                is Int -> {
                    Resource.error(IdResourceString(response),null)
                }
                else -> {
                    Resource.error(response as ResourceString,null)
                }
            }
        }catch (e:Exception){
            Timber.e(e)
            return Resource.error(TextResourceString(e.message!!))
        }
    }


    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!dataManager.isNetworkConnectionAvailable()) {
            return TextResourceString("No internet connection")
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                TextResourceString(ErrorUtils.getCommonErrorFromCode(responseCode))
            }
        } catch (e: IOException) {
            return TextResourceString("Failed to connect")
        }
    }

    override suspend fun login(
        userName: String,
        password: String
    ): Resource<LoginResponse, ResourceString> {

        if(BuildConfig.DEBUG){
            if(userName.equals("test") and password.equals("test")){
                return Resource.success(LoginResponse(true,"token"))
            }
            return Resource.Companion.error(TextResourceString("Invalid credentials"))
        }

        return  mapResponse(processCall { dataManager.apiInterface.login(userName,password) })
    }
}