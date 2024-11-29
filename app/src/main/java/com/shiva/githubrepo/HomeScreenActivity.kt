package com.shiva.githubrepo

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: RepositoryAdapter
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.repositoryRecyclerView)

        // Set up RecyclerView with PagingAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter()
        recyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        // Observe search results
        viewModel.searchResults.observe(this, Observer { result ->
            result?.let {
//                progressBar.visibility = View.GONE
                adapter.submitData(lifecycle, it) // Submit the paginated data to the adapter
            }
        })

        // Observe loading state
//        viewModel.loading.observe(this, Observer {
//            progressBar.visibility = if (it) View.VISIBLE else View.GONE
//        })

        // Handle search input
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchRepositories(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}
