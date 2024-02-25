package com.example.myapplication.model


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIService {
    @GET
    fun getCharacterByName(@Url url:String): Call<DogResponse>

    @FormUrlEncoded
    @POST("./")
    fun getTest(@Field("datum") encodeValue:String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("./")
    fun getNotification(@Field("datum") encodeValue:String): Call<NotificationSingleModel>
}