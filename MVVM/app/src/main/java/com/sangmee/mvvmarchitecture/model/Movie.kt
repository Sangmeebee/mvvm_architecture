package com.sangmee.mvvmarchitecture.model

data class Movie(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
