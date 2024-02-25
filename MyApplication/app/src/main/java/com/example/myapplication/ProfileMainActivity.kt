package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityProfileMainBinding
import com.example.myapplication.databinding.ActivityRemindBinding
import com.example.myapplication.utils.DialogDate
import com.example.myapplication.utils.SharedPreference
import com.example.myapplication.utils.ToolbarActivity

class ProfileMainActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityProfileMainBinding>(this,R.layout.activity_profile_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickListener=this
        initializeToolbar(binding.toolbarRemind,"Perfil",true,false)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imProfile->{
                val changePage = Intent(this, ProfileActivity::class.java)
                startActivity(changePage)
            }

        }
    }

}