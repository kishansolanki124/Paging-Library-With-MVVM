package com.example.paginglibrarywithmvvm.users.network

import com.example.paginglibrarywithmvvm.users.dtos.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Created by Morris on 03,June,2019
 */
interface ApiService {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<UserResponse>
}