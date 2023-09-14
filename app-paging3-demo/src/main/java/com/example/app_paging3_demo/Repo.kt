package com.example.app_paging3_demo

data class RepoRes<T: Any>(
    val items: List<T>
)

data class Repo(
    val id: Int,

    val name: String,

    val description: String,

    val starCount: Int
)