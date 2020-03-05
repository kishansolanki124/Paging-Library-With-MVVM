package com.example.paginglibrarywithmvvm.news.datasource

import androidx.paging.PageKeyedDataSource
import com.example.paginglibrarywithmvvm.news.dtos.NewsListDto
import com.example.paginglibrarywithmvvm.news.network.ApiService
import com.example.paginglibrarywithmvvm.news.network.ApiServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataSource : PageKeyedDataSource<Int, NewsListDto.Article>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsListDto.Article>
    ) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getBitcoinNews(FIRST_PAGE, "37996235d5ec446b80e686b4cb238514")
        call.enqueue(object : Callback<NewsListDto> {

            override fun onFailure(call: Call<NewsListDto>, t: Throwable) {

            }

            override fun onResponse(call: Call<NewsListDto>, response: Response<NewsListDto>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.articles
                    responseItems.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsListDto.Article>
    ) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getBitcoinNews(params.key, "37996235d5ec446b80e686b4cb238514")
        call.enqueue(object : Callback<NewsListDto> {
            override fun onResponse(call: Call<NewsListDto>, response: Response<NewsListDto>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.articles
                    val key = params.key + 1
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<NewsListDto>, t: Throwable) {
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsListDto.Article>
    ) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getBitcoinNews(params.key, "37996235d5ec446b80e686b4cb238514")
        call.enqueue(object : Callback<NewsListDto> {
            override fun onResponse(call: Call<NewsListDto>, response: Response<NewsListDto>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.articles
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<NewsListDto>, t: Throwable) {
            }
        })
    }

    companion object {
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }
}