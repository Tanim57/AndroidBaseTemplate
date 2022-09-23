package com.tanim.androidbasetemplate.managers

interface PreferenceManager {

    fun getApiToken(): String?

    fun setApiToken(apiToken: String?)

    fun getEmail(): String?

    fun getUser(): String?

    fun setUser(user: String?)

}