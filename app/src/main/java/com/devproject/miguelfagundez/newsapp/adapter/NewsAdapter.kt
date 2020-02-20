package com.devproject.miguelfagundez.newsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devproject.miguelfagundez.newsapp.R
import com.devproject.miguelfagundez.newsapp.model.Article
import com.devproject.miguelfagundez.newsapp.model.NewsObject
import com.devproject.miguelfagundez.newsapp.util.Config
import com.squareup.picasso.Picasso

class NewsAdapter(var listNews : List<Article>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listNews.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.apply {

           newsAuthor.text = listNews.get(position).author?.toString()?:"Unknown author"
           newsSource.text = listNews.get(position).source?.name?:"Unknown source"
           newsTitle.text = listNews.get(position).title

           Picasso.get().load(listNews.get(position).urlToImage).into(newsImage)

           itemView.setOnClickListener {view ->
                val intent = Intent(Config.KEY_WEBSITE_FILTER_ADAPTER)
                intent.putExtra(Config.KEY_WEBSITE_VALUE, listNews.get(position).url)
                itemView.context.sendBroadcast(intent)
           }
       }
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val newsImage = view.findViewById<ImageView>(R.id.iv_news_image)
        val newsAuthor = view.findViewById<TextView>(R.id.tv_news_author)
        val newsSource = view.findViewById<TextView>(R.id.tv_news_source)
        val newsTitle = view.findViewById<TextView>(R.id.tv_news_title)
    }
}