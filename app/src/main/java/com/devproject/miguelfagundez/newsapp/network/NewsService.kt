package com.devproject.miguelfagundez.newsapp.network

import com.devproject.miguelfagundez.newsapp.model.NewsObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/****************************************
 * NewsService interface.
 * Defining the services for this news app
 * @author Miguel Fagundez
 * @since 02/19/2020
 * @version 1.0
 **************************************/
interface NewsService {

    // Search Top Headlines
    @GET("/v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String ):Call<NewsObject>
}