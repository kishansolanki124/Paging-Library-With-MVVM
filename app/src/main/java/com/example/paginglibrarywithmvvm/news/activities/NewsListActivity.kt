package com.example.paginglibrarywithmvvm.news.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginglibrarywithmvvm.R
import com.example.paginglibrarywithmvvm.news.adapters.NewsListAdapter
import com.example.paginglibrarywithmvvm.news.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news_list_activity.*

class NewsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_list_activity)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemViewModel = ViewModelProviders.of(this)
            .get(NewsViewModel::class.java)

        val adapter = NewsListAdapter {
            //this is on click item, printing title of the news
            Log.d("news title:", it.title)
        }

        itemViewModel.newsPagedList.observe(this, Observer {
            adapter.submitList(it)
        })

        recyclerView.adapter = adapter
    }
}