package com.example.stateflow.repository

import android.util.Log
import com.example.stateflow.daggerbuilder.component.DaggerAppComponent
import com.example.stateflow.response.UsResponse
import com.example.stateflow.retrofit.ConnectionEndPoint
import com.example.stateflow.utils.Utils
import retrofit2.Response
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var api : ConnectionEndPoint

    init {
        DaggerAppComponent.create().inject(this)
    }

    suspend fun getUsList() : Response<UsResponse> {
        return api.getUsResponse(Utils.TESLA_KEY,Utils.API)
    }
}