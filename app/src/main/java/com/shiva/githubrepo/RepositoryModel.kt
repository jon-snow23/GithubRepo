package com.shiva.githubrepo

data class RepositoryModel(
    val id: Int,
    val name: String,
    val description: String?,
    val owner: Owner,
    val html_url: String?
)

//data class Owner(
//    val login: String,
//    val avatar_url: String
//)
