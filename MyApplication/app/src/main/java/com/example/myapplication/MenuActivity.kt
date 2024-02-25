package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.utils.SharedPreference
import com.example.myapplication.utils.ToolbarActivity

class MenuActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityMenuBinding>(this,R.layout.activity_menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.title1="Es un titulo"
        initializeToolbar(binding.toolbarMenu,"MENU",true,true)
        binding.clickListener=this
        binding.toolbarMenu.clickListener = this
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.lyTask->{
                val changePage = Intent(this, CheckInActivity::class.java)
                startActivity(changePage)
            }
            R.id.lyTask2->{
                val changePage = Intent(this, TaskActivity::class.java)
                startActivity(changePage)
            }
            R.id.ly333->{
                val changePage = Intent(this, NotificationActivity::class.java)
                startActivity(changePage)
            }
            R.id.ly444->{
                val changePage = Intent(this, NewAllActivity::class.java)
                startActivity(changePage)
            }
            R.id.ly5555->{
                val changePage = Intent(this, ProfileMainActivity::class.java)
                startActivity(changePage)
            }
            R.id.ivLogo->{
                createAlert("¿Salir?'","Hola Mundo",this)
            }
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
        binding.listener1=View.OnClickListener {
            val sharedPreference = SharedPreference ( this)
            sharedPreference.removeValue("session")
            sharedPreference.clearSharedPreference()
            val changePage = Intent(this, MainActivity::class.java)
            startActivity(changePage)
            finish()
            d.dismiss()
        }
        d.setCancelable(true)
        d.setCanceledOnTouchOutside(true)
        d.show()
    }

}
