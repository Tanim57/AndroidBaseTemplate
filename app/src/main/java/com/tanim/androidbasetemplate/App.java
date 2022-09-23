package com.tanim.androidbasetemplate;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.tanim.androidbasetemplate.di.component.AppComponent;
import com.tanim.androidbasetemplate.di.component.DaggerAppComponent;
import com.tanim.androidbasetemplate.logging.AppDebugTree;

import timber.log.Timber;


public class App extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static Context mContext;
    private final String TAG = App.class.getSimpleName();
    public AppComponent appComponent;


    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = App.this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new AppDebugTree());
        }

        appComponent = DaggerAppComponent.builder().application(this).build();
//        repositoryComponent = DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule(appComponent.getAPIInterface(),
//                appComponent.getDataManager())).build();

//        if (BuildConfig.DEBUG) {
//            com.facebook.stetho.Stetho.initializeWithDefaults(this);
//        }

        //TimeZone.setDefault(TimeZone.getTimeZone(Constant.DhakaTimeZone));


//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
//            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

//        FirebaseApp.initializeApp(this);
//
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}