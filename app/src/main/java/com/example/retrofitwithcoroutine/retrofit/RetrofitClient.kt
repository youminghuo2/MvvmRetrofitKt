package com.example.retrofitwithcoroutine.retrofit

import com.example.retrofitwithcoroutine.api.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        private lateinit var retrofit: Retrofit
        fun getInstance() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(Constants.DEBUG_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    private fun getOkHttpClient(): OkHttpClient {
        var logging = HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);


        return OkHttpClient.Builder()
                .connectTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(20L, TimeUnit.SECONDS)
                .readTimeout(20L, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)

}