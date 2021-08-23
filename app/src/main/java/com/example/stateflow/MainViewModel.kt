package com.example.stateflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val timerLiveData = MutableLiveData<String>()

    val timerStateFlow = MutableStateFlow<Int>(0)

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
}