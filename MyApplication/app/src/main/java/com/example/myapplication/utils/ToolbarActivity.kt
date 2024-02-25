package com.example.myapplication.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.databinding.ToolbarBinding

abstract class ToolbarActivity: AppCompatActivity(), View.OnClickListener{

    protected fun initializeToolbar(
        toolbarBinding: ToolbarBinding,
        title1: String = "",
        back:Boolean=false,
        isMain:Boolean=false,
        share:Boolean=false,
        ) {
        setSupportActionBar(toolbarBinding.toolbar)
        toolbarBinding.title = title1
        toolbarBinding.clickListener = this
        if(isMain) {
            toolbarBinding.ivLogo.visibility= View.VISIBLE
            toolbarBinding.toolbar.navigationIcon=null
            toolbarBinding.ivShare.visibility= View.GONE
            toolbarBinding.tvTitleBar.visibility= View.VISIBLE
        } else {
            toolbarBinding.tvTitleBar.visibility= View.VISIBLE
            toolbarBinding.ivLogo.visibility= View.GONE
            toolbarBinding.ivShare.visibility= View.GONE
        }

        if(share) {
            toolbarBinding.ivLogo.visibility= View.GONE
            toolbarBinding.ivShare.visibility= View.VISIBLE
        }

        if(back) {
            toolbarBinding.toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }
    protected fun initializeToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivLogo->{
                createAlert("¿Salir?'","Hola Mundo",this)
                val changePage = Intent(this, MainActivity::class.java)
                startActivity(changePage)
                finishAffinity()
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

    /*
    * override fun onClick(v: View?) {
        if (v?.id == R.id.ivLogo) {
            createAlert("¿Salir?'","¿Estas seguro de querer salir?) {
                    Intent(this, MainActivity::class.java).run {
                startActivity(this)
                finish()
            }
            }
        }
    }

    private fun createAlert(title: String, message: String, block: () -> Unit) {

        val builder = AlertDialog.Builder(this)
        val binding = DialogBinding.inflate(LayoutInflater.from(this))
        builder.setView(binding.root)
        binding.title=title
        binding.description=message

        val d = builder.create()
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.listener1=View.OnClickListener {
            d.dismiss()
            block.invoke()
        }
        d.setCancelable(true)
        d.setCanceledOnTouchOutside(true)
        d.show()
    }
    * */
}


