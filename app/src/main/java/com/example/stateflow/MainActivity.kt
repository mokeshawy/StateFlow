package com.example.stateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // connect with viewModel.
        binding.lifecycleOwner = this
        binding.mainActivity = mainViewModel

        /* ------ Flow ---- */

//        runBlocking {
//            // producer...step
//            flow<Int> {
//                for (i in 1..10){
//                    emit(i)
//                    delay(1000)
//                    Log.d("here Producer",i.toString())
//                }
//            }.filter { i : Int -> i <= 5} // intermediate step
//                .buffer().collect {
//                    delay(2000)
//                    Log.d("here Collector",it.toString())
//                } // collector step ... this suspend fun
//
//            // all step not working when collector not connect with all step. producer and intermediate.
//        }


        // when you have tow api call we need show result in one time create this logic.
//        runBlocking {
//
//            val flow1 = flow<Int> {
//                for (i in 1..3){
//                    emit(i)
//                }
//            }
//
//            val flow2 = flow<String> {
//                val list = listOf<String>("A","B","C")
//                for (i in list){
//                    emit(i)
//                }
//            }
//
//            flow1.zip(flow2){ a : Int , b : String -> "$a:$b"
//
//            }.collect {
//                Log.d("Here",it)
//            }
//        }


        /*  ---------- --------- State Flow --------- ------------*/

//        mainViewModel.startTimerLiveData()
//        mainViewModel.timerLiveData.observe(this, Observer {
//            binding.tvMainActivity.text = it
//            Log.d("here",it)
//        })

        mainViewModel.startTimerStateFlow()
        lifecycleScope.launchWhenStarted {
            mainViewModel.timerStateFlow.collect {
                binding.tvMainActivity.text = it.toString()
                Log.d("here",it.toString())
            }
        }


    }
}