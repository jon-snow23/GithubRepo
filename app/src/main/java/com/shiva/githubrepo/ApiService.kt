package com.shiva.githubrepo

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Search repositories based on a query and page number
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int
    ): RepositoryResponse

    // Fetch repository details by repo ID
    @GET("repositories/{repoId}")
    suspend fun getRepositoryDetails(
        @Path("repoId") repoId: Int
    ): RepositoryModel

    // Fetch the list of contributors for a specific repository
    @GET("repositories/{repoId}/contributors")
    suspend fun getContributors(
        @Path("repoId") repoId: Int
    ): List<Contributor>

    @GET("users/{username}/repos")
    suspend fun getRepositoriesByContributor(
        @Path("username") contributorLogin: String
    ): List<RepositoryModel> // List of repositories
}
