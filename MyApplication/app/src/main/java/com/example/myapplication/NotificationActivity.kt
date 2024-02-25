package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.NotificationAdapter
import com.example.myapplication.databinding.ActivityNotificationBinding
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.model.Notification
import com.example.myapplication.model.NotificationModel
import com.example.myapplication.model.NotificationSingleModel
import com.example.myapplication.model.User
import com.example.myapplication.utils.AlertDialogCustom
import com.example.myapplication.utils.SharedPreference
import com.example.myapplication.utils.ToolbarActivity
import com.example.myapplication.utils.WaitingDialog
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64

class NotificationActivity : ToolbarActivity(), View.OnClickListener {

    private val waitingDialog by lazy {
        WaitingDialog(this)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityNotificationBinding>(this, R.layout.activity_notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToolbar(binding.toolbarNotifications,"Notificaciones",true,false)
        binding.clickListener = this
        //binding.toolbarNotifications.clickListener = this

        //binding.bottomBar.clickListener = this
        val sharedPreference = SharedPreference ( this)
        Toast.makeText(this,"email: "+ sharedPreference, Toast.LENGTH_LONG).show()
        //val originalString = "105|"+ sharedPreference.getValueInt("idUser")
        val originalString = "105|3"

        val encodedString: String = Base64.getEncoder().encodeToString(originalString.toByteArray())
        waitingDialog.showGeneric()
        getNotificationList(encodedString)


    }
    override fun onClick(v: View?) {
        when (v?.id) {


        }
    }

    private fun getNotificationList(encodedString: String) {
        doAsync {
            val call = getRetrofit().create(APIService::class.java).getNotification(encodedString).execute()

            uiThread {
                if (call.isSuccessful) {
                    val response = call.body() as NotificationSingleModel
                    waitingDialog.hideWDialog()
                    if (response.valido == 1) {
                        if(response.addenda!!.isNotEmpty()){
                            setNotificationList(response.addenda!!)
                        }

                            //setSessionUser(response.addenda[0])
                    } else {
                        AlertDialogCustom.createAlert(this@NotificationActivity,"¡Sistema!",response.mensaje.toString())
                    }
                } else {
                    waitingDialog.hideWDialog()
                    AlertDialogCustom.createAlert(this@NotificationActivity,"¡Sistema!","Error al consultar.")
                }
            }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cognitus.administradordemo.info/processrequest/processrequest-add/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun setNotificationList(list: List<Notification>) {


        binding.rvNotification.layoutManager = LinearLayoutManager ( this)
        val adapter = NotificationAdapter ( this, ArrayList<Notification>(list))
//adding the adapter to recyclerview
        binding.rvNotification.adapter = adapter
    }
}