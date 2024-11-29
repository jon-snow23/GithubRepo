package com.shiva.githubrepo

class RepoDetailsRepository {

    private val apiService = RetrofitInstance.api

    suspend fun getRepoDetails(repoId: Int): RepositoryModel {
        // Use the API service to fetch repository details by ID
        // For example, you might need to use `getRepositoryDetails` endpoint (adjust as necessary)
        val response = apiService.getRepositoryDetails(repoId)
        return response
    }

    suspend fun getContributors(repoId: Int): List<Contributor> {
        // Use the API service to fetch contributors for the given repository
        val response = apiService.getContributors(repoId)
        return response
    }
}
