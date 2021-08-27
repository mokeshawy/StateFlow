package com.example.stateflow.daggerbuilder.module

import com.example.stateflow.repository.Repository
import com.example.stateflow.retrofit.ConnectionEndPoint
import com.example.stateflow.utils.Utils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideUsApi() : ConnectionEndPoint{
        return Retrofit.Builder().baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConnectionEndPoint::class.java)
    }

    @Provides
    fun providerUsService() : Repository{
        return Repository()
    }
}