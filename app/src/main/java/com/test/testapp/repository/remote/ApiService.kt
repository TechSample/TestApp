package com.test.testapp.repository.remote

import com.test.testapp.model.UserEntity
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET


/**
 * The interface which provides methods to get result of REST webservices
 */
interface ApiService {
    /**
     * Get the list of the users from the API
     */
    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}