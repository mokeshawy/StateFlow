package com.example.stateflow.ui.mainactivity


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stateflow.daggerbuilder.component.DaggerAppComponent
import com.example.stateflow.repository.Repository
import com.example.stateflow.state.Resource
import com.example.stateflow.response.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel() : ViewModel() {

    val timerLiveData = MutableLiveData<String>()

    val timerStateFlow = MutableStateFlow<Int>(0)

    val resultFromAdapter = MutableStateFlow<Resource<List<Article>>>(Resource.loading(null))

    @Inject
    lateinit var repository : Repository
    init {
        DaggerAppComponent.create().inject(this)
    }

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
        viewModelScope.launch(Dispatchers.IO) {
            try{ // when no internet connection will no crash ui.
                val response =  repository.getUsList()
                resultFromAdapter.catch { e ->
                    resultFromAdapter.value = (Resource.error(e.toString(),null))
                }.collect {
                    viewModelScope.launch (Dispatchers.Main){
                        resultFromAdapter.value = (Resource.success(response.body()!!.articles))
                        Log.d("test", "Api${repository.api}")
                    }

                }
            }catch (e:Exception){

            }
        }
    }
}