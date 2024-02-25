package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.databinding.ActivityNewBinding
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.utils.ToolbarActivity
import kotlin.math.log

class NewActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityNewBinding>(this,R.layout.activity_new)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val description=intent.getStringExtra(  "description")
        val title=intent. getStringExtra(  "title")
        binding.title=title
        binding.description=description
        initializeToolbar(binding.toolbarNew,"Noticias",true,false,true)
        binding.clickListener=this
        binding.toolbarNew.clickListener = this
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivLogo->{
                createAlert("Compartir'","Hola Mundo",this)
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
            Handler(Looper.getMainLooper()).postDelayed({
                val changePage = Intent(this, MainActivity::class.java)
                startActivity(changePage)
                finish()
            },100)
            d.dismiss()
        }
        d.setCancelable(true)
        d.setCanceledOnTouchOutside(true)
        d.show()
    }

}