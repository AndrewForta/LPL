package com.andrewfortner.list_network

import com.andrewfortner.list_network.model.PostDto
import kotlinx.serialization.InternalSerializationApi
import retrofit2.Call
import retrofit2.http.GET


internal interface InternalScheduleService {
    @OptIn(InternalSerializationApi::class)
    @GET("1/comments")
    fun getPosts(): Call<List<PostDto>> {
        return ApiClient.apiService.getPosts()
    }
}
