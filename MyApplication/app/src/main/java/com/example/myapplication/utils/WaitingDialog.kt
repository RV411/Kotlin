package com.example.myapplication.utils

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.myapplication.R

class WaitingDialog  (private val context: Context?) {

    private var simpleProgressDialog: AlertDialog? = null

    fun showMessage(mensaje: String, coerce: Boolean) {
        if (coerce)
            simpleProgressDialog = null
        showMessage(mensaje)
    }

    private fun showMessage(message: String?) {
        if (context != null && simpleProgressDialog == null) {
            val builder = AlertDialog.Builder(context)
            val viewDialog = LayoutInflater.from(context).inflate(
                R.layout.waiting_dialog, null)
            (viewDialog.findViewById<TextView>(R.id.mensajeEspera)).text = message
            builder.setView(viewDialog)
            simpleProgressDialog = builder.create()
            simpleProgressDialog?.setCancelable(false)
            simpleProgressDialog?.setCanceledOnTouchOutside(false)
            simpleProgressDialog?.show()
        } else
            Log.e(javaClass.simpleName,
                "No se puede mostrar dialogo: contexto nulo? " + (context == null) + ", " +
                        "dialogo existente? " + (simpleProgressDialog != null))
    }

    fun showGeneric(coerce: Boolean = false) {
        if (coerce)
            simpleProgressDialog = null
        showMessage(context?.getString(R.string.extra_mensajeespera))
    }
    fun hideWDialog() {
        simpleProgressDialog?.dismiss()
        simpleProgressDialog = null
    }
}