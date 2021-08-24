package com.example.stateflow.retrofit

import com.example.stateflow.response.UsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionEndPoint {

    @GET("v2/top-headlines")
    suspend fun getUsResponse(@Query("country") country : String ,
                              @Query("category") category : String ,
                              @Query("apiKey") apiKey : String) : Response<UsResponse>
}