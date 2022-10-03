package com.tanim.androidbasetemplate.works;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;

import com.tanim.androidbasetemplate.managers.DataManager;


public class WorkerFactory extends androidx.work.WorkerFactory {

    private DataManager dataManager;

    public WorkerFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Nullable
    @Override
    public CoroutineWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        if(workerClassName.equals(SyncWorker.class.getName())){
            return new SyncWorker(appContext,workerParameters,dataManager);
        }
        return null;
    }
}
