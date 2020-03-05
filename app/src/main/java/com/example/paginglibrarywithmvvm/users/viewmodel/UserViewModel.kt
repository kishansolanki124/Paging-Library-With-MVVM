package com.example.paginglibrarywithmvvm.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paginglibrarywithmvvm.users.datasource.UserDataSource
import com.example.paginglibrarywithmvvm.users.datasource.UserDataSourceFactory
import com.example.paginglibrarywithmvvm.users.dtos.User

/**
 * Created by Morris on 03,June,2019
 */
class UserViewModel : ViewModel() {
    var userPagedList: LiveData<PagedList<User>>
    private var liveDataSource: LiveData<UserDataSource>

    init {
        val itemDataSourceFactory =
            UserDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}