package com.andrewfortner.list_network.model

import com.andrewfortner.domain.Post
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class PostDto(
    val url: String,
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
) {
    companion object {
        fun toPost(postDto: PostDto): Post {
            return Post(
                body = postDto.body,
                email = postDto.email,
                id = postDto.id,
                name = postDto.name,
                postId = postDto.postId,
                iconImageFileName = "",
                iconImageAsString = "",
            )
        }
    }
}