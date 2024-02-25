package com.example.myapplication.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.myapplication.databinding.DialogAlertBinding

object AlertDialogCustom {

    fun createAlert(context: Context,
                    title: String?,
                    message: String) {
        val builder = AlertDialog.Builder(context)

        val binding = DialogAlertBinding.inflate(LayoutInflater.from(context))
        binding.title = title
        binding.message = message

        builder.setView(binding.root)
        val d = builder.create()
        binding.btnDAcp.setOnClickListener(View.OnClickListener {
            d.dismiss()
        })


        d.setCancelable(false)
        d.setCanceledOnTouchOutside(true)
        d.show()
    }
}