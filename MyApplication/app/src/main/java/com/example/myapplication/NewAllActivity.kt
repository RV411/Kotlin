package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.NewAdapter
import com.example.myapplication.adapter.NotificationAdapter
import com.example.myapplication.databinding.ActivityNewAllBinding
import com.example.myapplication.databinding.ActivityNotificationBinding
import com.example.myapplication.model.NewModel
import com.example.myapplication.model.NotificationModel
import com.example.myapplication.utils.ToolbarActivity

class NewAllActivity : ToolbarActivity(), View.OnClickListener {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityNewAllBinding>(this, R.layout.activity_new_all)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToolbar(binding.toolbarNewAll,"Noticias",true,false)
        binding.clickListener = this
        //binding.toolbarNotifications.clickListener = this

        //binding.bottomBar.clickListener = this

        //adding a layoutmanager
        binding.rvNewAll.layoutManager = LinearLayoutManager(this)

        //crating an array list to store users using the data class user
        val news = ArrayList<NewModel>()

        //adding some dummy data to the list
        news.add(NewModel("Titulo 1","Descripción de la noticia 1","L"))
        news.add(NewModel("Titulo 2","Descripción de la noticia 2","R"))
        news.add(NewModel("Titulo 3","Descripción de la noticia 3","L"))

        //creating our adapter
        val adapter = NewAdapter(this, news)

        //adding the adapter to recyclerview
        binding.rvNewAll.adapter = adapter

    }
    override fun onClick(v: View?) {
        when (v?.id) {


        }
    }
}