package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.utils.ToolbarActivity

class RegisterActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityRegisterBinding>(this,R.layout.activity_register)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickListener=this
        initializeToolbar(binding.toolbarRegister,"Registrar",true,false)
    }

    override fun onClick(v: View?) {

    }
}