package com.example.paginglibrarywithmvvm.news.datasource

import androidx.lifecycle.MutableLiveData
import com.example.paginglibrarywithmvvm.news.dtos.NewsListDto

class NewsDatasourceFactory : androidx.paging.DataSource.Factory<Int, NewsListDto.Article>() {

    val newsLiveDataSource = MutableLiveData<NewsDataSource>()

    override fun create(): androidx.paging.DataSource<Int, NewsListDto.Article> {
        val newsDataSource = NewsDataSource()
        newsLiveDataSource.postValue(newsDataSource)
        return newsDataSource
    }
}