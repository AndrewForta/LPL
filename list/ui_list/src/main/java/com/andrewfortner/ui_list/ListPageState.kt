package com.andrewfortner.ui_list

import android.content.Context
import com.andrewfortner.domain.Post

data class ListPageState(
    val context: Context? = null,
    val postList: List<Post>? = null,
    val loading: Boolean = false,
    val selectedPost: Post? = null,
)