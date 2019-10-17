package com.test.testapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testapp.model.UserEntity
import com.test.testapp.repository.AppRepository

class DashboardViewModel(val context: Context) : ViewModel() {

    var appRepository: AppRepository

    init {
        appRepository = AppRepository.getInstance(context)
    }

    fun getAllUsersList(): LiveData<List<UserEntity>>
    {
        return appRepository.getUsers()
    }

    fun getAllFavUsersList(): LiveData<List<UserEntity>>
    {
        return appRepository.getAllFavUsers()
    }

    fun getUsersFromAPIAndStore()
    {
        appRepository.retrieveDataFromAPI()
    }


    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return DashboardViewModel(context) as T
        }
    }

    fun updateUser(isFav : Int, userId : Int)
    {
        appRepository.updateUser(isFav,userId)
    }


}