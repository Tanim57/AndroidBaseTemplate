package com.tanim.androidbasetemplate.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tanim.androidbasetemplate.data.User
import javax.inject.Singleton

@Singleton
@Database(
    entities = [User::class],
    version = 53
)
abstract class AppDatabase : RoomDatabase() {

    companion object{

        private var database: AppDatabase? = null

        fun createDatabase(context: Context): AppDatabase {
            if (database == null) {
                synchronized(AppDatabase::class.java) {
                    if (database == null) database = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "app_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return database!!
        }
    }




}