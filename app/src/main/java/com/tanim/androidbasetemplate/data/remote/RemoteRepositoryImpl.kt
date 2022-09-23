package com.tanim.androidbasetemplate.di.component
import Resource
import com.tanim.androidbasetemplate.data.Constant
import com.tanim.androidbasetemplate.data.mapper.RemoteRepository
import com.tanim.androidbasetemplate.data.mapper.ResourceString
import com.tanim.androidbasetemplate.data.mapper.TextResourceString
import com.tanim.androidbasetemplate.managers.DataManager
import com.tanim.androidbasetemplate.utils.ErrorUtils
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteRepositoryImpl (private val dataManager: DataManager) : RemoteRepository {
    override suspend fun getPackages(user: Int): Resource<ResourceString, ResourceString> {
        TODO("Not yet implemented")
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
}