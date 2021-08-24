package com.example.stateflow.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun makeRetrofit() : ConnectionEndPoint{
        return Retrofit.Builder().baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConnectionEndPoint::class.java)
    }
}