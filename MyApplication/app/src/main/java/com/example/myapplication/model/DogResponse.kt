package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DogResponse (@SerializedName("status") @Expose var status:String,
                   @SerializedName("message")@Expose var images: List<String>){}