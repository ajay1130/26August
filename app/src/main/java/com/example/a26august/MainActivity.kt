package com.example.a26august

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a26august.Adapter.StudentAdpater
import com.example.a26august.Model.Student
import com.example.a26august.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var listStudentData:ArrayList<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        listStudentData = ArrayList<Student>().apply {
            add(Student(1,"ajay","mca"))
            add(Student(2,"karan","m.com"))
            add(Student(3,"vijay","mtech"))
            add(Student(4,"shubham","m.b.a"))
        }
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                shuffleData()
            }
        }
        shuffleData()
        binding.buttonWorkerActivity.setOnClickListener {
            Intent(this,WorkerActivity::class.java).also {
                startActivity(it)
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.refresh_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_refresh -> {
                binding.swipeRefresh.apply {
                    binding.swipeRefresh.isRefreshing = true
                    shuffleData()
                }
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }


    private fun shuffleData(){
        binding.apply {
            recylerviewStudent.apply {
                layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                setHasFixedSize(true)
                adapter = StudentAdpater(listStudentData.shuffled())
            }

            swipeRefresh.apply{
                if(isRefreshing){
                    isRefreshing = false
                }
            }
        }
    }
}