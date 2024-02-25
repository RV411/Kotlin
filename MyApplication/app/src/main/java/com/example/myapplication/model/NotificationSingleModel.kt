package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotificationSingleModel (
    @SerializedName("valido")@Expose val valido:Int?,
    @SerializedName("mensaje")@Expose val mensaje:String?,
    @SerializedName("addenda")@Expose val addenda:List<Notification>?
)