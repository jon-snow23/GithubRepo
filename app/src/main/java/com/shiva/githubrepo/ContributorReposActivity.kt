package com.shiva.githubrepo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContributorReposActivity : AppCompatActivity() {

    private lateinit var repoRecyclerView: RecyclerView
    private lateinit var noReposTextView: TextView
    private lateinit var viewModel: ContributorReposViewModel
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor_repos)

        // Initialize views
        repoRecyclerView = findViewById(R.id.contributorReposRecyclerView)
        noReposTextView = findViewById(R.id.contributorNameTextView)

        // Initialize RecyclerView with LinearLayoutManager
        repoRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter()
        repoRecyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ContributorReposViewModel::class.java)

        // Get contributor login from Intent
        val contributorLogin = intent.getStringExtra("CONTRIBUTOR_LOGIN")
        if (contributorLogin != null) {
            viewModel.getContributorRepos(contributorLogin)
        }

        // Observe repository list
        viewModel.repos.observe(this, Observer { repos ->
            if (repos.isNullOrEmpty()) {
                repoRecyclerView.visibility = View.GONE
                noReposTextView.visibility = View.VISIBLE
            } else {
                repoRecyclerView.visibility = View.VISIBLE
                noReposTextView.visibility = View.GONE
//                adapter.submitList(repos)
            }
        })

        // Observe loading state (optional)
        viewModel.loading.observe(this, Observer {
            // Optionally show a loading spinner
        })
    }
}
