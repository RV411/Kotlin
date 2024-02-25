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
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityRemindBinding
import com.example.myapplication.databinding.ActivityTaskSingleBinding
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.utils.DialogDate
import com.example.myapplication.utils.SharedPreference
import com.example.myapplication.utils.ToolbarActivity

class TaskSingleActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityTaskSingleBinding>(this,R.layout.activity_task_single)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickListener=this
        initializeToolbar(binding.toolbarRemind,"Tareas",true,false)
        binding.title=intent. getStringExtra(  "title")
        binding.title2=intent. getStringExtra(  "title2")
        binding.title3=intent. getStringExtra(  "title3")
        binding.description=intent.getStringExtra(  "description")
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.spHr->{
                DialogDate.showWatch(this, binding.spHr)
            }
            R.id.btOne->{
                createAlert("Hola","Desplazaste 1",this)
            }
            R.id.btSec->{
                createAlert("Hola","Desplazaste 2",this)
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