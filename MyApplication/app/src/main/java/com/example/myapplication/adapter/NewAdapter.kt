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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.NewActivity
import com.example.myapplication.R
import com.example.myapplication.RemindActivity
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.databinding.ItemNewBinding
import com.example.myapplication.databinding.ItemNotificationBinding
import com.example.myapplication.model.NewModel
import com.example.myapplication.model.NotificationModel

class NewAdapter(private var context: Context, private var list: ArrayList<NewModel>):
    RecyclerView.Adapter<NewAdapter.ViewHolder>() {
    class ViewHolder(var bind: ItemNewBinding): RecyclerView.ViewHolder(bind.root)
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val bind = ItemNewBinding.inflate(inflater,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind.noticia = list[position]
        holder.bind.root.setOnClickListener {
            val changePage = Intent(context, NewActivity::class.java)
            changePage.putExtra("description",  list[position].description)
            changePage.putExtra("title", list[position].title)
            context.startActivity(changePage)
        }
    }
}