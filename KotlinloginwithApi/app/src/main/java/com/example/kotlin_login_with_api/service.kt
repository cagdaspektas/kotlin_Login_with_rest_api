package com.example.kotlin_login_with_api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api{
    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email")email:String,
        @Field("password")password:String

    ):Call<LoginResponseModel>
}