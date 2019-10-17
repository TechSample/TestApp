package com.test.testapp.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.testapp.model.AddressEntity
import com.test.testapp.model.CompanyEntity
import com.test.testapp.model.UserEntity
import com.test.testapp.repository.local.dao.UserDao
import com.test.testapp.repository.local.typeconverters.AddressConverter
import com.test.testapp.repository.local.typeconverters.CompanyConverter

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(AddressConverter::class,CompanyConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}