package com.tanim.androidbasetemplate.utils

import com.tanim.androidbasetemplate.R
import com.tanim.androidbasetemplate.utils.ErrorMessage
import java.net.HttpURLConnection

object ErrorUtils {
    fun getCommonErrorFromCode(code: Int): String {
        return when (code) {
            HttpURLConnection.HTTP_NO_CONTENT -> ErrorMessage.ERROR_204
            HttpURLConnection.HTTP_BAD_REQUEST -> ErrorMessage.ERROR_400
            HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorMessage.ERROR_401
            HttpURLConnection.HTTP_FORBIDDEN -> ErrorMessage.ERROR_403
            HttpURLConnection.HTTP_NOT_FOUND -> ErrorMessage.ERROR_404
            HttpURLConnection.HTTP_PROXY_AUTH -> ErrorMessage.ERROR_407
            HttpURLConnection.HTTP_CLIENT_TIMEOUT -> ErrorMessage.ERROR_408
            429 -> ErrorMessage.ERROR_429
            HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorMessage.ERROR_500
            HttpURLConnection.HTTP_BAD_GATEWAY -> ErrorMessage.ERROR_502
            HttpURLConnection.HTTP_UNAVAILABLE -> ErrorMessage.ERROR_503
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ErrorMessage.ERROR_504
            511 -> ErrorMessage.ERROR_511
            else -> "Unknown Error!!"
        }
    }

//    fun getCommonErrorResourceFromCode(code: Int): Int {
//        return when (code) {
//            HttpURLConnection.HTTP_NO_CONTENT -> R.string.error_204
//            HttpURLConnection.HTTP_BAD_REQUEST -> R.string.error_400
//            HttpURLConnection.HTTP_UNAUTHORIZED -> R.string.error_401
//            HttpURLConnection.HTTP_FORBIDDEN -> R.string.error_403
//            HttpURLConnection.HTTP_NOT_FOUND -> R.string.error_404
//            HttpURLConnection.HTTP_PROXY_AUTH -> R.string.error_407
//            HttpURLConnection.HTTP_CLIENT_TIMEOUT -> R.string.error_408
//            429 -> R.string.error_429
//            HttpURLConnection.HTTP_INTERNAL_ERROR -> R.string.error_500
//            HttpURLConnection.HTTP_BAD_GATEWAY -> R.string.error_502
//            HttpURLConnection.HTTP_UNAVAILABLE -> R.string.error_503
//            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> R.string.error_504
//            511 -> R.string.error_511
//            else -> R.string.error_unknown
//        }
//    }
}