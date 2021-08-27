package com.example.stateflow.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.stateflow.R
import com.example.stateflow.adapter.TeslaAdapter
import com.example.stateflow.databinding.ActivityMainBinding
import com.example.stateflow.onclickinterface.OnClick
import com.example.stateflow.response.Article
import com.example.stateflow.state.Status
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),OnClick{

    lateinit var binding        : ActivityMainBinding
    private val mainViewModel   : MainViewModel by viewModels()
     private var teslaAdapter =  TeslaAdapter(arrayListOf(),this)

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

        // simple test for fetch data from api with stateflow.
        binding.recyclerView.adapter = teslaAdapter
        binding.swipLayOut.setOnRefreshListener {
            mainViewModel.fetchData()
            GlobalScope.launch {
                binding.swipLayOut.isRefreshing = false
                binding.swipLayOut.setColorSchemeResources(R.color.colorPrimary)
                delay(3000)
            }
        }
        mainViewModel.fetchData()
        lifecycleScope.launchWhenStarted {
            mainViewModel.resultFromAdapter.collect {
                when(it.status){
                    Status.SUCCESS ->{
                        binding.progressBar.visibility = View.GONE
                        // call fun addData from adapter.
                        teslaAdapter.addData(it.data!!)
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

    override fun teslaOnClick(viewHolder: TeslaAdapter.ViewHolder, tesla: Article, position: Int) {
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(this,tesla.title.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}