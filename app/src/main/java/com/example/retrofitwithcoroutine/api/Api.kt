package com.example.retrofitwithcoroutine.api

import com.example.retrofitwithcoroutine.model.JokeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("getJoke")
    fun getJokeList(@Query("page")page:Int,@Query("count")count:Int,@Query("type")type:String): Call<JokeModel>
}