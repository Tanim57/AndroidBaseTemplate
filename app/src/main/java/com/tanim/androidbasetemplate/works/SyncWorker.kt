package com.tanim.androidbasetemplate.works

import android.content.Context
import androidx.work.*
import com.tanim.androidbasetemplate.managers.DataManager
import com.tanim.androidbasetemplate.data.database.AppDatabase

class SyncWorker(context: Context, workerParams: WorkerParameters, dataManager: DataManager) :
    CoroutineWorker(context, workerParams) {
    private val dataManager: DataManager
    private val database: AppDatabase

    init {
        database = dataManager.database
        this.dataManager = dataManager
    }

    override suspend fun doWork(): Result {
        val dataBuilder = Data.Builder()
        //TODO work here

        if(!dataManager.isLoggedIn()){
            dataBuilder.putString("error","No user found")
            return Result.failure(dataBuilder.build())
        }

        return Result.success()
    }

}