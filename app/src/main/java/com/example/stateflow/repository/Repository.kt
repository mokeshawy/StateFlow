package com.example.stateflow.repository

import com.example.stateflow.daggerbuilder.component.DaggerAppComponent
import com.example.stateflow.response.UsResponse
import com.example.stateflow.retrofit.ConnectionEndPoint
import retrofit2.Response
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var api : ConnectionEndPoint

    init {
        DaggerAppComponent.create().inject(this)
    }

    suspend fun getUsList() : Response<UsResponse> {
        return api.getUsResponse("us","business","9b3d814ad7e840fa97fa9608886787f5")
    }
}