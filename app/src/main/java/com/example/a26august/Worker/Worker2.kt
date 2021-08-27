package com.example.a26august.Worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class Worker2(context: Context, workParams:WorkerParameters):Worker(context,workParams) {
    override fun doWork(): Result {
        doLog()
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        Log.d("MainActivity", "Worker2: Finished work..")
    }

    private fun doLog(){
        var num = 1
        while (!isStopped && num<=5){
            Thread.sleep(1000)
            Log.d("MainActivity", "Worker2: ${num++}")

        }
    }

}