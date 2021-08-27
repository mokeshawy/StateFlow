package com.example.stateflow.retrofit

import com.example.stateflow.response.UsResponse
import com.example.stateflow.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionEndPoint {

    @GET(Utils.END_POINT)
    suspend fun getUsResponse(@Query(Utils.Q_KEY) country : String ,
                              @Query(Utils.API_KEY) apiKey : String) : Response<UsResponse>
}