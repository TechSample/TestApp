package com.test.testapp.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.testapp.model.UserEntity

@Dao
interface UserDao {
    @get:Query("SELECT * FROM UserEntity")
    val all: LiveData<List<UserEntity>>

    @Insert
    fun insert(vararg user: UserEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userList: List<UserEntity>)

    @Query("DELETE FROM UserEntity")
    fun deleteAllUsers()

    @Query("UPDATE UserEntity SET isFavourite = :isFav WHERE id = :userid")
    fun updateUserById(isFav : Int, userid : Int)

    @Query("SELECT * FROM UserEntity where isFavourite = 1")
    fun getAllFavUsers():LiveData<List<UserEntity>>
}