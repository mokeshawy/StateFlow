package com.example.stateflow.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.R
import com.example.stateflow.UsAdapter
import com.example.stateflow.databinding.ActivityMainBinding
import com.example.stateflow.mainactivity.state.Status
import com.example.stateflow.response.Article
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
     var usAdapter =  UsAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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

//        mainViewModel.startTimerStateFlow()
//        lifecycleScope.launchWhenStarted {
//            mainViewModel.timerStateFlow.collect {
//                binding.tvMainActivity.text = it.toString()
//                Log.d("here",it.toString())
//            }
//        }

        // simple test for fetch data from api with stateFlow.
        binding.recyclerView.adapter = usAdapter
        mainViewModel.fetchData()
        lifecycleScope.launchWhenStarted {
            mainViewModel.resultFromAdapter.collect {
                when(it.status){
                    Status.SUCCESS ->{
                        binding.progressBar.visibility = View.GONE
                        usAdapter.addData(it.data!!)
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    Status.LOADING ->{
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    Status.ERROR ->{
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity,it.message,Toast.LENGTH_LONG ).show()
                    }
                }
            }
        }
    }
}