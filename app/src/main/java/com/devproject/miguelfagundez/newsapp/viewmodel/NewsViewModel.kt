package com.devproject.miguelfagundez.newsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devproject.miguelfagundez.newsapp.model.Article
import com.devproject.miguelfagundez.newsapp.model.NewsObject
import com.devproject.miguelfagundez.newsapp.network.NewsRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsLiveData = MutableLiveData<List<Article>>()

    private val retrofit = NewsRetrofit()

    private val TAG = "TAG_NEWS_MESSAGE"


    fun getTopHeadlines(country : String) : MutableLiveData<List<Article>>{
        retrofit.searchNews(country)
            .enqueue(object : Callback<NewsObject>{
                override fun onFailure(call: Call<NewsObject>, t: Throwable) {
                    Log.d(TAG,"Error: ${t.message}")
                }

                override fun onResponse(call: Call<NewsObject>, response: Response<NewsObject>) {
                    response.body()?.let { topHeadlines ->
                        newsLiveData.value = topHeadlines.articles
                        Log.d(TAG,"Title: ${topHeadlines.articles.get(0).title}")
                    }
                }
            })
        return newsLiveData
    }
}