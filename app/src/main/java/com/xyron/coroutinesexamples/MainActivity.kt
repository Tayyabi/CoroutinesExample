package com.xyron.coroutinesexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.concurrent.thread


const val TAG = "MYCOROUTINESCODE"

class MainActivity : AppCompatActivity() {


    lateinit var counterText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        counterText = findViewById(R.id.counter)
        Log.d(TAG, "${Thread.currentThread().name}")


        CoroutineScope(Dispatchers.Main).launch {
            task1()
        }

        CoroutineScope(Dispatchers.Main).launch {
            task2()
        }


    }


    fun updateCounter(view: View) {

        Log.d(TAG, "${Thread.currentThread().name}")

        counterText.text = "${counterText.text.toString().toInt()+1}"

    }




    fun doAction(view: View) {

        CoroutineScope(Dispatchers.IO).launch {
            executeLongRunningTask()
            Log.d(TAG, "1- ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "2- ${Thread.currentThread().name}")
        }

        MainScope().launch(Dispatchers.Main) {
            Log.d(TAG, "3- ${Thread.currentThread().name}")
        }
        /*thread(start = true) {
            executeLongRunningTask()
        }*/
    }

    private fun executeLongRunningTask(){
        for (i in 1..1000000000L) {

        }
    }


    suspend fun task1(){
        Log.d(TAG, "STARTING TASK 1")
        yield()
        Log.d(TAG, "ENDING TASK 1")
    }

    suspend fun task2(){
        Log.d(TAG, "STARTING TASK 2")
        yield()
        Log.d(TAG, "ENDING TASK 2")
    }
}