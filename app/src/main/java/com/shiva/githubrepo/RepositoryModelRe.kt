package com.shiva.githubrepo

data class RepositoryModelRe(
    val id: Int,
    val name: String,
    val description: String?,
    val owner: Owner
)

data class Owner(
    val login: String,
    val avatar_url: String
)
