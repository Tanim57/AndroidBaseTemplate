
package com.tanim.androidbasetemplate.di.module

import AppSession
import com.tanim.androidbasetemplate.data.database.AppDatabase.Companion.createDatabase
import javax.inject.Singleton
import android.app.Application
import android.content.Context
import com.tanim.androidbasetemplate.data.database.AppDatabase
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tanim.androidbasetemplate.utils.JsonDeserializerUtils
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import android.util.DisplayMetrics
import com.tanim.androidbasetemplate.managers.*
import com.tanim.androidbasetemplate.utils.DateFormat
import dagger.Module
import dagger.Provides
import okhttp3.Request
import java.lang.Exception
import java.time.DayOfWeek
import java.time.LocalTime

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return createDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAPIInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, dataManager: PreferenceManager): Retrofit {
        val gson = GsonBuilder()
            .setDateFormat(DateFormat.DATE_TIME_FORMAT)
            .registerTypeAdapter(LocalTime::class.java, JsonDeserializerUtils.deserializerLocalTime)
            .registerTypeAdapter(DayOfWeek::class.java, JsonDeserializerUtils.deserializerDayOfWeek)
            .create()
        val serviceUrl = ""
        return Retrofit.Builder()
            .baseUrl(
                serviceUrl
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        authorizationInterceptor: AuthorizationInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @get:Singleton
    @get:Provides
    val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    @Provides
    @Singleton
    fun providePreferenceManager(context: Context): PreferenceManager {
        return AppPreferenceManager(context)
    }

    @Provides
    @Singleton
    fun provideSessionManager(managerManager: PreferenceManager): Session {
        return AppSession(managerManager)
    }

    @Provides
    @Singleton
    fun provideInterceptor(session: Session): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder
            requestBuilder = try {
                if (session.isLoggedIn() && session.getApiToken() != null) {
                    original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", session.getApiToken())
                } else {
                    original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                }
            } catch (e: Exception) {
                original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
            }
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideAuthorizationInterceptor(session: Session): AuthorizationInterceptor {
        return AuthorizationInterceptor(session)
    }

    @Provides
    @Singleton
    fun provideDisplayMetrics(context: Context): DisplayMetrics {
        return context.resources.displayMetrics
    }
}