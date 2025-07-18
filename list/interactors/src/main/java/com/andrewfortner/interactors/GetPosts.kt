package com.andrewfortner.interactors

import com.andrewfortner.domain.Post
import com.andrewfortner.list_network.ScheduleServiceImpl

fun getPosts(): List<Post>? = ScheduleServiceImpl().getPosts()