package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User (@SerializedName("user_id") @Expose var userId:Int,
            @SerializedName("user_name") @Expose var userName:String,
            @SerializedName("user_mail") @Expose var userMail:String,
            @SerializedName("user_job_title") @Expose var userJobTitle:String
)