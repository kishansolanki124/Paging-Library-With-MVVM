package com.example.paginglibrarywithmvvm.news.network

import com.example.paginglibrarywithmvvm.news.dtos.NewsListDto
import com.example.paginglibrarywithmvvm.users.dtos.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //https://newsapi.org/v2/everything?q=bitcoin&apiKey=37996235d5ec446b80e686b4cb238514&page=1
    @GET("everything?q=bitcoin")
    fun getBitcoinNews(@Query("page") page: Int, @Query("apiKey") apiKey: String): Call<NewsListDto>
}