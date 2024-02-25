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
import com.example.myapplication.databinding.ActivityCheckInBinding
import com.example.myapplication.databinding.ActivityTaskSingleBinding
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.utils.DialogDate
import com.example.myapplication.utils.ToolbarActivity

class CheckInActivity : ToolbarActivity(), View.OnClickListener {

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityCheckInBinding>(this,R.layout.activity_check_in)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickListener=this
        initializeToolbar(binding.toolbarCheck,"Check In",true,false)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.spHr->{
                DialogDate.showWatch(this, binding.spHr)
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
        binding.listener1= View.OnClickListener {
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