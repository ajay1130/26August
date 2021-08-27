package com.example.a26august.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a26august.Model.Student
import com.example.a26august.databinding.ItemStudentBinding

class StudentAdpater(private val studentList:List<Student>):RecyclerView.Adapter<StudentAdpater.StudentViewHolder>() {

    class StudentViewHolder(private val binding:ItemStudentBinding):RecyclerView.ViewHolder(binding.root) {
        fun binding(curStudent: Student){
            binding.textIdStudent.text = curStudent.id.toString()
            binding.textCourseStudent.text = curStudent.course
            binding.textNameStudent.text = curStudent.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return StudentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val curStudent = studentList[position]
        holder.binding(curStudent)


    }

    override fun getItemCount(): Int = studentList.size
}