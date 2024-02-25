package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.databinding.ItemNotificationBinding
import com.example.myapplication.model.Notification
import com.example.myapplication.model.NotificationModel

class NotificationAdapter(private var context: Context, private var list: ArrayList<Notification>):
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    class ViewHolder(var bind: ItemNotificationBinding): RecyclerView.ViewHolder(bind.root)
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val bind = ItemNotificationBinding.inflate(inflater,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind.notification = list[position]
        holder.bind.circle = if(list[position].notStatus!! == 1){
            ContextCompat.getDrawable(context, R.drawable.circle_notification)
        }else{
            ContextCompat.getDrawable(context, R.drawable.circle_notification_gray)
        }
        holder.bind.root.setOnClickListener {
            createAlert(list[position].notTitle!!,list[position].notDesc!!,context)
        }
    }

    private fun createAlert(title: String,message: String,context: Context) {

        val builder = AlertDialog.Builder(context)
        val binding = DialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.title=title
        binding.description=message

        val d = builder.create()
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.listener1= View.OnClickListener {
            d.dismiss()
        }
        d.setCancelable(true)
        d.setCanceledOnTouchOutside(true)
        d.show()
    }
}