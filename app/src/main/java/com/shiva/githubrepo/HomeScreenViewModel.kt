package com.shiva.githubrepo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.launch


class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository()
    val searchResults: LiveData<PagingData<RepositoryModel>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun searchRepositories(query: String) {
        true.also { loading.value = it }

        // Use Paging3 with a PagingSource
        viewModelScope.launch {
            val flow = repository.getRepositories(query)
//            searchResults.value = Pager(PagingConfig(pageSize = 10)) { flow }.flow.cachedIn(viewModelScope)
        }
    }
}
