package com.example.stateflow.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stateflow.mainactivity.state.Resource
import com.example.stateflow.response.Article
import com.example.stateflow.response.UsResponse
import com.example.stateflow.retrofit.RetrofitBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val timerLiveData = MutableLiveData<String>()

    val timerStateFlow = MutableStateFlow<Int>(0)

    val resultFromAdapter = MutableStateFlow<Resource<List<Article>>>(Resource.loading(null))

    fun startTimerLiveData(){
        viewModelScope.launch {
            val list = listOf<Int>(1,1,1,2,2,2,3,4,5,6,6,6,7,8,8,8,9) // live Data do not differentiate between duplicate.
            for (i in list){
                timerLiveData.value = i.toString()
                delay(1000)
            }
        }
    }

    fun startTimerStateFlow(){
        viewModelScope.launch {
            val list = listOf<Int>(1,1,1,2,2,2,3,4,5,6,6,6,7,8,8,8,9) // State Flow do not duplicate data
            for (i in list){
                timerStateFlow.emit(i)
                delay(1000)
            }
        }
    }

    // fetch data from api.
    fun fetchData(){
        viewModelScope.launch {
           val response =  RetrofitBuilder.makeRetrofit().getUsResponse("us","business","9b3d814ad7e840fa97fa9608886787f5")
            resultFromAdapter.catch { e ->
                resultFromAdapter.value = (Resource.error(e.toString(),null))
            }.collect {
                resultFromAdapter.value = (Resource.success(response.body()!!.articles))
            }
        }
    }
}