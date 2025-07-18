package com.andrewfortner.list_network

import com.andrewfortner.list_network.model.PostDto
import kotlinx.serialization.InternalSerializationApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class InternalScheduleServiceImpl : InternalScheduleService {
    @OptIn(InternalSerializationApi::class)
    override fun getPosts(): Call<List<PostDto>> {
        return ApiClient.apiService.getPosts()
    }
}

internal object RetrofitClient {
    private const val BASE_URL = "http://jsonplaceholder.typicode.com/posts/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

internal object ApiClient {
    val apiService: InternalScheduleService by lazy {
        RetrofitClient.retrofit.create(InternalScheduleService::class.java)
    }
}