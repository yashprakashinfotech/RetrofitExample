package com.yash.retrofitexample

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//All articles about Tesla from the last month, sorted by recent first
//https://newsapi.org/v2/everything?q=tesla&from=2021-12-04&sortBy=publishedAt&apiKey=API_KEY
//Top business headlines in the US right now
//https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=0ab4f671472e44fca0d710033ee4aeda

const val  BASE_URL = "https://newsapi.org/"
const val API_KEY = "0ab4f671472e44fca0d710033ee4aeda"

interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country: String,@Query("page")page: Int) : Call<News>

    // https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}

object NewsService{

    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }

}