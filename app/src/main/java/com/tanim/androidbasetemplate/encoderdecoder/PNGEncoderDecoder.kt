package com.tanim.androidbasetemplate.encoderdecoder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/**
 * Created by Tanim on 10/14/2020.
 */
class PNGEncoderDecoder : EncoderDecoder {
    override fun decode(bytes: ByteArray?): Bitmap? {
        try {
            return bytes?.size?.let { BitmapFactory.decodeByteArray(bytes, 0, it) }
        } catch (e: Exception) {
        }
        return null
    }

    override fun encode(bitmap: Bitmap?, factor: Int): ByteArray? {
        return try {
            val stream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.PNG, factor, stream)
            stream.toByteArray()
        } catch (e: Exception) {
            null
        }
    }
}