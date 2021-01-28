package com.example.retrofitwithcoroutine.response

import com.example.retrofitwithcoroutine.api.Api
import com.example.retrofitwithcoroutine.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object JokeResponse {
    private val weatherService = RetrofitClient.getInstance().create(Api::class.java)

    suspend fun getJokeList(page: Int, count: Int, type: String) = weatherService.getJokeList(page, count, type).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}