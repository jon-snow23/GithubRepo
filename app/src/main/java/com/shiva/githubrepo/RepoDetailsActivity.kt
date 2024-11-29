package com.shiva.githubrepo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var repoOwnerImageView: ImageView
    private lateinit var repoNameTextView: TextView
    private lateinit var repoDescriptionTextView: TextView
    private lateinit var projectLinkTextView: TextView
    private lateinit var contributorRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: RepoDetailsViewModel
    private lateinit var contributorAdapter: ContributorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        repoOwnerImageView = findViewById(R.id.repoOwnerImageView)
        repoNameTextView = findViewById(R.id.repoNameTextView)
        repoDescriptionTextView = findViewById(R.id.repoDescriptionTextView)
        projectLinkTextView = findViewById(R.id.repoProjectLinkTextView)
        contributorRecyclerView = findViewById(R.id.contributorsRecyclerView)
//        progressBar = findViewById(R.id.progressBar)

        // Set up RecyclerView
        contributorRecyclerView.layoutManager = LinearLayoutManager(this)
        contributorAdapter = ContributorAdapter()
        contributorRecyclerView.adapter = contributorAdapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(RepoDetailsViewModel::class.java)

        // Get repository ID from intent
        val repoId = intent.getIntExtra("REPO_ID", -1)
        if (repoId != -1) {
            viewModel.getRepoDetails(repoId)
        }

        // Observe repository details
        viewModel.repoDetails.observe(this, Observer { repo ->
            repo?.let {
                progressBar.visibility = View.GONE
                repoNameTextView.text = it.name
                repoDescriptionTextView.text = it.description
                Glide.with(this).load(it.owner.avatar_url).into(repoOwnerImageView)
                projectLinkTextView.text = it.html_url

                projectLinkTextView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.toString()))
                    startActivity(intent)
                }

                // Load contributors
                viewModel.getContributors(repoId)
            }
        })

        // Observe contributors
        viewModel.contributors.observe(this, Observer { contributors ->
            contributorAdapter.submitList(contributors)
        })

        // Observe loading state
        viewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}
