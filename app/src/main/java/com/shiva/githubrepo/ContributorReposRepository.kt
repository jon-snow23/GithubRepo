package com.shiva.githubrepo

class ContributorReposRepository {

    private val apiService = RetrofitInstance.api

    suspend fun getContributorRepos(contributorLogin: String): List<RepositoryModel> {
        // Fetch repositories contributed by the given contributor
        val response = apiService.getRepositoriesByContributor(contributorLogin)
        return response // Return the list of repositories
    }
}
