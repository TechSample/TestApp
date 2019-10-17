package com.test.testapp

import android.app.Application
import androidx.room.Room
import com.test.testapp.repository.local.database.AppDatabase

class TestApp : Application(){

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, AppDatabase::class.java, "test_app_db").fallbackToDestructiveMigration().build()
    }
}