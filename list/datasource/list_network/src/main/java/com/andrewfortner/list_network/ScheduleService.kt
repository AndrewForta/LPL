package com.andrewfortner.list_network

import com.andrewfortner.domain.Post


interface ScheduleService {
    fun getPosts(): List<Post>?
}