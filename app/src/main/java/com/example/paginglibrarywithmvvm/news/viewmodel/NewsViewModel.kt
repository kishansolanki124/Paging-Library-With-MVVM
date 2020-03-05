package com.example.paginglibrarywithmvvm.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paginglibrarywithmvvm.news.datasource.NewsDataSource
import com.example.paginglibrarywithmvvm.news.datasource.NewsDataSource.Companion.PAGE_SIZE
import com.example.paginglibrarywithmvvm.news.datasource.NewsDatasourceFactory
import com.example.paginglibrarywithmvvm.news.dtos.NewsListDto

class NewsViewModel : ViewModel() {

    var newsPagedList: LiveData<PagedList<NewsListDto.Article>>
    private var liveDataSource: LiveData<NewsDataSource>

    init {
        val itemDataSourceFactory = NewsDatasourceFactory()
        liveDataSource = itemDataSourceFactory.newsLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        newsPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}