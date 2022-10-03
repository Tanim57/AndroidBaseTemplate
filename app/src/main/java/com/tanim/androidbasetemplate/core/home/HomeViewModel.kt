package com.tanim.androidbasetemplate.core.home

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanim.androidbasetemplate.base.BaseViewModel
import com.tanim.androidbasetemplate.data.reporitory.DataRepository
import com.tanim.androidbasetemplate.encoderdecoder.EncoderDecoderFactory
import com.tanim.androidbasetemplate.encoderdecoder.EncodingFormat
import com.tanim.androidbasetemplate.managers.DataManager
import timber.log.Timber

class HomeViewModel(dataManager: DataManager,
                    repository: DataRepository
) :BaseViewModel(dataManager, repository) {

    private val encodeDecoder = EncoderDecoderFactory.getEncoderDecoder(EncodingFormat.JP2);


    fun encode(image: Bitmap):ByteArray?{
        return encodeDecoder.encode(image,70)
    }

    fun decode(byteArray: ByteArray?):Bitmap?{
        return encodeDecoder.decode(byteArray)
    }

}