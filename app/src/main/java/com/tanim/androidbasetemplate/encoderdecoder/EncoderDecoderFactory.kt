package com.tanim.androidbasetemplate.encoderdecoder

/**
 * Created by Tanim on 10/14/2020.
 */
object EncoderDecoderFactory {
    private var jp2EncoderDecoder: JP2EncoderDecoder? = null
    private var pngEncoderDecoder: PNGEncoderDecoder? = null
    private var jpegEncoderDecoder: JPEGEncoderDecoder? = null

    fun getEncoderDecoder(encodingFormat: EncodingFormat): EncoderDecoder {
        if (encodingFormat == EncodingFormat.JP2) {
            return jp2EncoderDecoder ?: JP2EncoderDecoder().also { jp2EncoderDecoder = it }
        } else if (encodingFormat == EncodingFormat.JPEG) {
            return jpegEncoderDecoder ?: JPEGEncoderDecoder().also { jpegEncoderDecoder = it }
        } else if (encodingFormat == EncodingFormat.PNG) {
            return pngEncoderDecoder ?: PNGEncoderDecoder().also { pngEncoderDecoder = it }
        }
        return JPEGEncoderDecoder()
    }
}