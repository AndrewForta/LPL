package com.andrewfortner.domain

data class Post(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int,
    val iconImageFileName: String,
    val iconImageAsString: String,
)