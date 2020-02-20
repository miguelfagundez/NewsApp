package com.devproject.miguelfagundez.newsapp.network

import android.graphics.Bitmap
import com.devproject.miguelfagundez.newsapp.model.NewsObject
import com.devproject.miguelfagundez.newsapp.util.Config
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/****************************************
 * NewsRetrofit class.
 *
 * @author Miguel Fagundez
 * @since 02/19/2020
 * @version 1.0
 **************************************/
class NewsRetrofit {

    private val retrofit : Retrofit
    private val service :NewsService

    init{
        retrofit = createRetrofit()
        service = createService(retrofit)
    }

    private fun createService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Config.BASE_URL)
            .build()
    }

    fun searchNews(country : String) : Call<NewsObject>{
        return service.getTopHeadlines(country, Config.API_KEY)
    }

}