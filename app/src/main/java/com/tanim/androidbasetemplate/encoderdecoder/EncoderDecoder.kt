package com.tanim.androidbasetemplate.encoderdecoder

import android.graphics.Bitmap

/**
 * Created by Tanim on 10/14/2020.
 */
interface EncoderDecoder {
    fun decode(bytes: ByteArray?): Bitmap?
    fun encode(bitmap: Bitmap?, factor: Int): ByteArray?
}

