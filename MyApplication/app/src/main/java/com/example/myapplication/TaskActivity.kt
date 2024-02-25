package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.NotificationAdapter
import com.example.myapplication.adapter.TaskAdapter
import com.example.myapplication.databinding.ActivityNotificationBinding
import com.example.myapplication.databinding.ActivityTaskBinding
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.ToolbarActivity

class TaskActivity : ToolbarActivity(), View.OnClickListener {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityTaskBinding>(this, R.layout.activity_task)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToolbar(binding.toolbarNotifications,"Tareas",true,false)
        binding.clickListener = this
        //binding.bottomBar.clickListener = this

        //adding a layoutmanager
        binding.rvNotification.layoutManager = LinearLayoutManager(this)

        //crating an array list to store users using the data class user
        val notifications = ArrayList<TaskModel>()

        //adding some dummy data to the list
        notifications.add(TaskModel("Titulo 1","Descripción del mensaje","L","segundo","L","5:00","L"))
        notifications.add(TaskModel("Titulo 1","Descripción del mensaje","R","segundo","R","12:00","L"))
        notifications.add(TaskModel("Titulo 1","Descripción del mensaje","L","segundo","L","1:00","R"))

        //creating our adapter
        val adapter = TaskAdapter(this, notifications)

        //adding the adapter to recyclerview
        binding.rvNotification.adapter = adapter

    }
    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}