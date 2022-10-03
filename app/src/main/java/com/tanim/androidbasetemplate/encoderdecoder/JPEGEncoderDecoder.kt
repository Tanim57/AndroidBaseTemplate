package com.tanim.androidbasetemplate.encoderdecoder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/**
 * Created by Tanim on 10/14/2020.
 */
class JPEGEncoderDecoder : EncoderDecoder {
    override fun decode(bytes: ByteArray?): Bitmap? {
        try {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes!!.size)
        } catch (e: Exception) {
        }
        return null
    }

    override fun encode(bitmap: Bitmap?, factor: Int): ByteArray? {
        return try {
            val stream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, factor, stream)
            stream.toByteArray()
        } catch (e: Exception) {
            null
        }
    }
}