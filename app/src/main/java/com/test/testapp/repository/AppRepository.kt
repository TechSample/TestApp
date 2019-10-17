package com.test.testapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.test.testapp.TestApp
import com.test.testapp.model.UserEntity
import com.test.testapp.repository.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppRepository {

    var executor : Executor?= null
    companion object {
        private var appRepository: AppRepository? = null
        private var context: Context ?= null
        private var retrofit: Retrofit ?= null
        val BASE_URL = "https://jsonplaceholder.typicode.com/"


        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): AppRepository {
            this.context = context
            if (appRepository == null){
                appRepository = AppRepository()
                val gson = Gson()
                retrofit =  Retrofit.Builder()

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BASE_URL)
                    .build()

            }
            return appRepository!!
        }
    }

    init {

        executor = Executors.newSingleThreadExecutor()

    }

    fun getUsers() : LiveData<List<UserEntity>>
    {
        return TestApp.database!!.userDao().all

    }

    fun getAllFavUsers() : LiveData<List<UserEntity>>
    {
        return TestApp.database!!.userDao().getAllFavUsers()

    }


    fun retrieveDataFromAPI()
    {


        val restApi = retrofit!!.create<ApiService>(ApiService::class.java)

        restApi.getUsers().enqueue(object : Callback<List<UserEntity>> {

            override fun onFailure(call: Call<List<UserEntity>>?, t: Throwable?) {
               Log.e("","OOPS!! something went wrong..")
            }

            override fun onResponse(call: Call<List<UserEntity>>?, response: Response<List<UserEntity>>?) {

               // Log.e(TAG,response!!.body().toString())
                when(response!!.code())
                {
                    200 ->{
                        Thread(Runnable {

                            TestApp.database!!.userDao().deleteAllUsers()
                            TestApp.database!!.userDao().insertAll(response.body()!!)

                        }).start()
                    }
                }

            }
        })


    }


    fun updateUser(isFav : Int, userId : Int){

        executor!!.execute {
            TestApp.database!!.userDao().updateUserById(isFav,userId)
        }

    }

}