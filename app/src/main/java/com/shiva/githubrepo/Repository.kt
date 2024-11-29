package com.shiva.githubrepo

import androidx.paging.PagingSource
import androidx.paging.PagingState

class Repository {

    private val apiService = RetrofitInstance.api

    fun getRepositories(query: String): PagingSource<Int, RepositoryModel> {
        return object : PagingSource<Int, RepositoryModel>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
                val page = params.key ?: 1
                val response = apiService.searchRepositories(query, page)
                return try {
                    LoadResult.Page(
                        data = response.items,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (response.items.isEmpty()) null else page + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
                return state.anchorPosition
            }
        }
    }
}
