package com.andrewfortner.list_network

import com.andrewfortner.domain.Post
import com.andrewfortner.list_network.model.PostDto
import kotlinx.serialization.InternalSerializationApi

class ScheduleServiceImpl : ScheduleService {
    @OptIn(InternalSerializationApi::class)
    override fun getPosts(): List<Post>? {
        val result = InternalScheduleServiceImpl().getPosts().execute().body()
        result?.let {
            return it.map { postDto ->
                PostDto.toPost(postDto)
            }
        }

        return null
    }
}