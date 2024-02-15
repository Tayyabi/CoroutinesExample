package com.xyron.coroutinesexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }
    }


    private suspend fun execute(){

        val parentJob = GlobalScope.launch(Dispatchers.Main) {

            Log.d(TAG, "Parent Started")
            Log.d(TAG, "Parent - ${coroutineContext}")

            val childJob = launch(Dispatchers.IO) {
                Log.d(TAG, "Child Job Started")
                Log.d(TAG, "Child - ${coroutineContext}")
                delay(5000)
                Log.d(TAG, "Child Job Ended")

            }

            delay(3000)
            Log.d(TAG, "Parent Ended")
        }

        delay(1000)
        parentJob.cancel()
        parentJob.join()
        Log.d(TAG, "Parent Completed")
    }
}