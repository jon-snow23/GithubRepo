package com.shiva.githubrepo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RepoDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepoDetailsRepository()
    val repoDetails: MutableLiveData<RepositoryModel> = MutableLiveData()
    val contributors: MutableLiveData<List<Contributor>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getRepoDetails(repoId: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                val repo = repository.getRepoDetails(repoId)
                repoDetails.value = repo
                loading.value = false
            } catch (e: Exception) {
                loading.value = false
                // Handle error if needed
            }
        }
    }

    fun getContributors(repoId: Int) {
        viewModelScope.launch {
            try {
                val contributorList = repository.getContributors(repoId)
                contributors.value = contributorList
            } catch (e: Exception) {
                // Handle error if needed
            }
        }
    }
}
