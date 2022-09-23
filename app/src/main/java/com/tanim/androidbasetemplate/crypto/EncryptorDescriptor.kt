package com.tanim.androidbasetemplate.crypto

import android.util.Base64
import com.tanim.androidbasetemplate.crypto.Encrypt.Companion.bytesToHex
import org.apache.commons.lang.StringEscapeUtils
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.lang.Exception

class EncryptorDescriptor {
    // No line break
    fun getBase64(input: String): String {
        try {
            return Base64.encodeToString(input.toByteArray(charset("UTF-8")), Base64.NO_WRAP)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return Base64.encodeToString(input.toByteArray(), Base64.NO_WRAP)
    }

    fun getFromBase64(base64: String?): String {
        val dataDec = Base64.decode(base64, Base64.NO_WRAP)
        var decodedString = ""
        try {
            decodedString = String(dataDec/*, "UTF-8"*/)
            return decodedString
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return String(dataDec)
    }

    companion object {
        fun textToUnicode(field: String?): String {
            return StringEscapeUtils.escapeJava(field)
        }

        fun textFromUnicode(field: String?): String? {
            return try {
                StringEscapeUtils.unescapeJava(field)
            } catch (e: Exception) {
                null
            }
        }

        fun decrypt(message: String?): String? {
            var message = message
            val mCrypt = Decrypt.instance
            try {
                message = String(mCrypt!!.decrypt(message)!!)
                message = textFromUnicode(message)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
            return message
        }

        fun encrypt(message: String?): String? {
            var message = message
            val mCrypt = Encrypt.instance
            message = try {
                bytesToHex(mCrypt!!.encrypt(textToUnicode(message))!!)
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e(e)
                return null
            }
            return message
        }
    }
}