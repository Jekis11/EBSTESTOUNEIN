package com.example.deutschland.model

data class MainModel(
    val count: Int,
    val current_page: Int,
    val per_page: Int,
    val results: List<Result>,
    val total_pages: Int
)