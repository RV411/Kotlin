package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.NewActivity
import com.example.myapplication.R
import com.example.myapplication.TaskSingleActivity
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.databinding.ItemNotificationBinding
import com.example.myapplication.databinding.ItemTaskBinding
import com.example.myapplication.model.NotificationModel
import com.example.myapplication.model.TaskModel

class TaskAdapter (private var context: Context, private var list: ArrayList<TaskModel>):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    class ViewHolder(var bind: ItemTaskBinding): RecyclerView.ViewHolder(bind.root)
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val bind = ItemTaskBinding.inflate(inflater,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind.task = list[position]
        if(list[position].icon2 == "R"){
            holder.bind.llAlert.visibility = View.GONE
        }

        if(list[position].icon3 == "R"){
            holder.bind.llTimer.visibility = View.GONE
        }

        holder.bind.rectangle = if(list[position].status == "L"){
            ContextCompat.getDrawable(context, R.drawable.rectangle_task)

        }else{
            ContextCompat.getDrawable(context, R.drawable.rectangle_task_green)
        }
        holder.bind.root.setOnClickListener {
            val changePage = Intent(context, TaskSingleActivity::class.java)
            changePage.putExtra("description",  list[position].description)
            changePage.putExtra("title", list[position].title)
            changePage.putExtra("title2", list[position].title2)
            changePage.putExtra("title3", list[position].title3)
            context.startActivity(changePage)
        }
    }

}