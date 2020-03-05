package com.example.paginglibrarywithmvvm.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginglibrarywithmvvm.R
import com.example.paginglibrarywithmvvm.news.dtos.NewsListDto
import kotlinx.android.synthetic.main.news_item.view.*

class NewsListAdapter(private val mListener: (NewsListDto.Article) -> Unit) :
    PagedListAdapter<NewsListDto.Article, NewsListAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsListDto = getItem(position)
        newsListDto?.let {
            holder.bind(it)
        }

        holder.itemView.setOnClickListener {
            newsListDto?.let { it1 -> mListener.invoke(it1) }
        }
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = view.ivNews
        private val userName = view.tvName
        private val userEmail = view.tvDesCription

        fun bind(article: NewsListDto.Article) {

            userName.text = article.title
            userEmail.text = article.description
            Glide.with(imageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.loading)
                .into(imageView)
        }
    }

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<NewsListDto.Article>() {
            override fun areItemsTheSame(
                oldItem: NewsListDto.Article,
                newItem: NewsListDto.Article
            ): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(
                oldItem: NewsListDto.Article,
                newItem: NewsListDto.Article
            ): Boolean =
                newItem == oldItem
        }
    }
}