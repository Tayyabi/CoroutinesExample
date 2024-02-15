package com.xyron.coroutinesexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        CoroutineScope(Dispatchers.IO).launch {

            printFollwers_1()
        }
    }

    private suspend fun printFollwers() {

        var fbFollowers = 0
        var instaFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }

        val job2 = CoroutineScope(Dispatchers.IO).launch {
            instaFollowers = getInstaFollowers()
        }

        job.join()
        job2.join()
        Log.d(TAG, "FB - ${fbFollowers} Insta - ${instaFollowers}")

    }

    private suspend fun printFollwers_1() {

        val fb = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }

        val insta = CoroutineScope(Dispatchers.IO).async {
           getInstaFollowers()
        }

        Log.d(TAG, "FB - ${fb.await()} Insta - ${insta.await()}")

    }


    private suspend fun printFollwers_3() {

        CoroutineScope(Dispatchers.IO).async {
            val fb = async { getFbFollowers() }
            val insta = async { getInstaFollowers() }

            Log.d(TAG, "FB - ${fb.await()} Insta - ${insta.await()}")
        }





    }


    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }


    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 113
    }
}