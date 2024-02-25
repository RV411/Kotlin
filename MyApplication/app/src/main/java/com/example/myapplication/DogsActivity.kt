package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.DogsAdapter
import com.example.myapplication.databinding.ActivityDogsBinding
import com.example.myapplication.model.DogResponse
import com.example.myapplication.utils.AlertDialogCustom
import com.example.myapplication.utils.ToolbarActivity
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.yesButton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogsActivity : ToolbarActivity() {
    lateinit var imagesPuppies: List<String>
    lateinit var newsAdapter: DogsAdapter
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityDogsBinding>(this, R.layout.activity_dogs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToolbar(binding.barraP, "Busqueda", true,false)
        binding.btnSearch.setOnClickListener {
            searchByName(binding.searchBreed.text.toString().toLowerCase())
        }
    }

    private fun searchByName(query: String) {
        doAsync {
            val call =
                getRetrofit().create(APIService::class.java).getCharacterByName("$query/images")
                    .execute()

            uiThread {
                if (call.isSuccessful) {
                    val puppies = call.body() as DogResponse
                    if (puppies.status == "success") {
                        initCharacter(puppies)
                    } else {
                        showErrorDialog()
                    }
                } else {
                    AlertDialogCustom.createAlert(this@DogsActivity,"¡Lo sentimos!","No hay datos que coincidan con su búsqueda.")
                }
            }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initCharacter(puppies: DogResponse) {
        if (puppies.status == "success") {
            imagesPuppies = puppies.images
        } else {
            alert("Sin datos.") {
                yesButton { }
            }.show()
        }
        newsAdapter = DogsAdapter(this, imagesPuppies)
        binding.rvDogs.setHasFixedSize(true)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = newsAdapter
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }
}