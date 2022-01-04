package com.yash.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var newsList : RecyclerView
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList = findViewById(R.id.newsList)

        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object : Callback<News>{

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Prakash","Error in Fetching News",t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news1 = response.body()

                if (news1!=null){
                    Log.d("Prakash",news1.toString())
                    adapter = NewsAdapter(this@MainActivity,news1.articles)
                    newsList.adapter = adapter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }



        })
    }
}