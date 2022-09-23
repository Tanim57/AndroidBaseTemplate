package com.tanim.androidbasetemplate.managers

import android.content.Context
import javax.inject.Singleton
import android.content.SharedPreferences
import kotlin.jvm.Synchronized
import com.tanim.androidbasetemplate.data.Constant

//import androidx.room.TypeConverters;
/**
 * Created by tanim on 11/15/2017.
 */
@Singleton
class AppPreferenceManager(context: Context
) : PreferenceManager {
    private val sharedPreferences: SharedPreferences


    @Synchronized
    override fun getApiToken(): String? {
        //SharedPreferences sharedPreferences = getInstance();//getSharedPreferences(Constant.LOGIN_SETTINGS);
        return sharedPreferences.getString(Constant.API_TOKEN, null)
        //Timber.i("ApiToken %s",TypeConverterUtils.decrypt(apiToken,false));

    }

    override fun setApiToken(apiToken: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(Constant.API_TOKEN, apiToken)
        editor.apply()
    }

    override fun getEmail(): String? {
        TODO("Not yet implemented")
    }

    override fun getUser(): String? {
        TODO("Not yet implemented")
    }

    override fun setUser(user: String?) {
        TODO("Not yet implemented")
    }


    init {
        sharedPreferences = context.getSharedPreferences("Preference", Context.MODE_PRIVATE)
    }
}