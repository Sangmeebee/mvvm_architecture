package com.sangmee.mvvmarchitecture.data.model

data class Movie(
    val display: Int,
    val items: ArrayList<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
