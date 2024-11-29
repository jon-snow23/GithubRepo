package com.shiva.githubrepo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContributorReposViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContributorReposRepository()
    val repos: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getContributorRepos(contributorLogin: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val contributorRepos = repository.getContributorRepos(contributorLogin)
                repos.value = contributorRepos
                loading.value = false
            } catch (e: Exception) {
                loading.value = false
                // Handle error, e.g., show a message to the user
            }
        }
    }
}
