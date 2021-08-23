package com.example.stateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



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


    }
}