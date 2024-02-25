package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Notification (@SerializedName("not_id") @Expose var notId:Int,
            @SerializedName("not_title") @Expose var notTitle:String,
            @SerializedName("not_desc") @Expose var notDesc:String,
            @SerializedName("not_status") @Expose var notStatus:Int
)