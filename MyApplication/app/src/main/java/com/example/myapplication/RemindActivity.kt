package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.databinding.ActivityRemindBinding
import com.example.myapplication.utils.ToolbarActivity

class RemindActivity: ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityRemindBinding>(this,R.layout.activity_remind)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickListener=this
        initializeToolbar(binding.toolbarRemind,"Recordar",true,false)
    }

    override fun onClick(v: View?) {

    }

}