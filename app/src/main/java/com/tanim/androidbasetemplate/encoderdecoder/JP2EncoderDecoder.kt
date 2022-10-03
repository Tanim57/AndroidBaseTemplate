package com.tanim.androidbasetemplate.encoderdecoder

import android.graphics.Bitmap
import com.gemalto.jp2.JP2Decoder
import com.gemalto.jp2.JP2Encoder

/**
 * Created by Tanim on 10/14/2020.
 */
class JP2EncoderDecoder : EncoderDecoder {
    override fun decode(bytes: ByteArray?): Bitmap? {
        return try {
            JP2Decoder(bytes).decode()
        } catch (e: Exception) {
            null
        }
    }

    override fun encode(bitmap: Bitmap?, factor: Int): ByteArray? {
        return try {
            JP2Encoder(bitmap).setCompressionRatio(factor.toFloat()).encode()
        } catch (e: Exception) {
            null
        }
    }
}