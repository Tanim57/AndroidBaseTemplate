package com.tanim.androidbasetemplate.managers

import androidx.databinding.ObservableField
import com.tanim.androidbasetemplate.data.database.AppDatabase


interface DataManager : PreferenceManager, Session, DataHelper {

    var database: AppDatabase
    var apiInterface:ApiInterface

    fun isNetworkConnectionAvailable(): Boolean

}