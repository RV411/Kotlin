package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogBinding
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.model.User
import com.example.myapplication.utils.AlertDialogCustom
import com.example.myapplication.utils.DialogDate
import com.example.myapplication.utils.SharedPreference
import com.example.myapplication.utils.WaitingDialog
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Base64

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val waitingDialog by lazy {
        WaitingDialog(this)
    }

    private val binding by lazy{
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickListener = this

        binding.swSomething.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
                Toast.makeText(this,"Se activo SwitchCompat", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"Se desactivo SwitchCompat", Toast.LENGTH_SHORT).show()
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvInf->{
                val changePage = Intent(this, RemindActivity::class.java)
                startActivity(changePage)
            }
            R.id.tvLnk->{
                val changePage = Intent(this, RegisterActivity::class.java)
                startActivity(changePage)
            }
            R.id.spHr->{
                DialogDate.showCalendar(this, binding.spHr)
            }
            R.id.btOne->{
                val mail = findViewById<EditText>(R.id.etMail);
                val pass = findViewById<EditText>(R.id.etPwd);
                val tMail = mail.text.toString()
                val tPass = pass.text.toString()
                Toast.makeText(this,"email: "+ tMail, Toast.LENGTH_LONG).show()
                val originalString = "100|"+ tMail +"|"+ tPass

                val encodedString: String = Base64.getEncoder().encodeToString(originalString.toByteArray())
                waitingDialog.showGeneric()
                getUserLogin(encodedString)
                val changePage = Intent(this, MenuActivity::class.java)
                startActivity(changePage)

/*val sharedPreference = SharedPreference ( this)
sharedPreference.save ("session", 1 )
val changePage = Intent(this, MenuActivity::class.java)
startActivity(changePage)*/

/*if (binding.swSomething.isChecked)
    Toast.makeText(this,"Activo", Toast.LENGTH_LONG).show()
else
    Toast.makeText(this,"desactivo", Toast.LENGTH_LONG).show()*/



/*val changePage = Intent(this, NotificationActivity::class.java)
startActivity(changePage)*/
/*val sharedPreference = SharedPreference ( this)
sharedPreference.save ("session", 1 )
val changePage = Intent(this, MenuActivity::class.java)
startActivity(changePage)*/
}
}
}

private fun getUserLogin(encodedString: String) {
doAsync {
val call = getRetrofit().create(APIService::class.java).getTest(encodedString).execute()

uiThread {
if (call.isSuccessful) {
    val response = call.body() as LoginResponse
    waitingDialog.hideWDialog()
    if (response.valido == 1) {
        if(response.addenda!!.isNotEmpty())
            setSessionUser(response.addenda[0])
    } else {
        AlertDialogCustom.createAlert(this@MainActivity,"¡Sistema!",response.mensaje.toString())
    }
} else {
    waitingDialog.hideWDialog()
    AlertDialogCustom.createAlert(this@MainActivity,"¡Sistema!","Error al consultar.")
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

private fun setSessionUser(user: User) {
Log.i("RES","Nombre "+user.userName)
    val sharedPreference = SharedPreference ( this)
    sharedPreference.save ("session",1 )
    sharedPreference.save ("idUser", user.userId )
}

/*val btOne = findViewById<Button>(R.id.btOne);
val tvName = findViewById<EditText>(R.id.etName);

val tvRegister = findViewById<TextView>(R.id.tvLnk);
val tvRemindPwd = findViewById<TextView>(R.id.tvInf);*/


/*btOne.setOnClickListener{
Toast.makeText(this, "Hola mundo", Toast.LENGTH_LONG).show()
}

btOne.setOnClickListener{
if(tvName.text.trim().isEmpty())
Toast.makeText(this, "no escribiste Nada", Toast.LENGTH_LONG).show()
else
Toast.makeText(this, "Hola "+tvName.text, Toast.LENGTH_LONG).show()
}

tvRegister.setOnClickListener {
val changePage = Intent(this, RegisterActivity::class.java)
startActivity(changePage)
}

tvRemindPwd.setOnClickListener {
val changePage = Intent(this, RemindActivity::class.java)
startActivity(changePage)
}*/
}