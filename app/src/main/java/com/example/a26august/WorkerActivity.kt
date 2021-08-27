package com.example.a26august

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.example.a26august.Worker.MainWorker
import com.example.a26august.Worker.Worker1
import com.example.a26august.Worker.Worker2
import com.example.a26august.Worker.Worker3
import com.example.a26august.databinding.ActivityWorkerBinding
import java.util.concurrent.TimeUnit

class WorkerActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityWorkerBinding
    private lateinit var mWorkerRequest:WorkRequest
    private lateinit var mWorkManager:WorkManager
    private lateinit var mWorker1:OneTimeWorkRequest
    private lateinit var mWorker2:OneTimeWorkRequest
    private lateinit var mWorker3:OneTimeWorkRequest
    private lateinit var mWorkManagerChaining:WorkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_worker)
        mWorkerRequest = PeriodicWorkRequestBuilder<MainWorker>(2,TimeUnit.MINUTES).build()
        mWorkManager = WorkManager.getInstance(this)

        mWorkManagerChaining = WorkManager.getInstance(this)
        mWorker1 = OneTimeWorkRequestBuilder<Worker1>().addTag("worker1").build()
        mWorker2 = OneTimeWorkRequestBuilder<Worker2>().addTag("worker2").build()
        mWorker3 = OneTimeWorkRequestBuilder<Worker3>().addTag("worker3").build()

        mBinding.apply {
            buttonStartWorker.setOnClickListener {
                mWorkManager.enqueue(mWorkerRequest)
            }
            buttonStopWorker.setOnClickListener {
                mWorkManager.cancelWorkById(mWorkerRequest.id)
            }

            buttonStartWorkerchaining.setOnClickListener {
                mWorkManagerChaining.beginWith(listOf(mWorker1,mWorker2)).then(mWorker3).enqueue()
            }
            buttonStopWorkerchaining.setOnClickListener {
                mWorkManagerChaining.cancelAllWorkByTag("worker3")
            }

        }
    }

    private fun createNotification(){
        val builder = NotificationCompat.Builder(this)
    }
}