package com.tanim.androidbasetemplate.utils


/**
 * Created by Tanim on 10/14/2020.
 */
object ErrorMessage {
    const val PASSWORD_LENGTH = 4
    const val GPS_REQUEST_CODE = 9001
    const val ERROR_204 = "No content found."
    const val ERROR_400 = "400, Bad request."
    const val ERROR_401 = "Authorization failed."
    const val ERROR_403 = "403, Accessing the resource you were trying to reach is forbidden"
    const val ERROR_404 = "404, The resource you were trying to reach is not found."
    const val ERROR_407 = "407, Required proxy authentication."
    const val ERROR_408 = "Request timeout."
    const val ERROR_429 = "Request cannot be processed at this time."
    const val ERROR_500 = "500, Server under maintenance. Please try sometime later."
    const val ERROR_502 = "502, Bad gateway."
    const val ERROR_503 = "503, Service temporary unavailable."
    const val ERROR_504 = "504, Gateway timeout."
    const val ERROR_511 = "511, Network authentication required."
    const val ERROR_UNKNOWN = "Unknown Error!!"
    const val ERROR_EMPTY_FIELD = "Field can't be empty"
    const val ERROR_PASSWORD_LENGTH = "Must be at least " + PASSWORD_LENGTH + " characters length"
    const val ERROR_PASSWORD_MISS_MATCH = "Password miss-match"
    const val NO_RESULT = "No result found"
    const val ERROR_FAILED_CONNECTION = "Failed to connect server. Please try again!"
    const val ERROR_NO_CONNECTION = "No network connection. Please check your connection!"
    const val ERROR_FAILED_LOAD = "Fail to load. Please try again"
    const val AUTHENTICATION_FAILED = "Authentication failed."
}