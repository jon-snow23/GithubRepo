package com.shiva.githubrepo

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shiva.githubrepo.databinding.RepositoryItemBinding


class RepositoryAdapterRe : ListAdapter<RepositoryModel, RepositoryAdapterRe.RepositoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = getItem(position)
        repository?.let {
            holder.bind(it)
        }
    }

    inner class RepositoryViewHolder(private val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryModel) {
            binding.repoNameTextView.text = repository.name
            binding.repoDescriptionTextView.text = repository.description
            Glide.with(binding.root.context).load(repository.owner.avatar_url).into(binding.repoImageView)

            // Open RepoDetailsActivity on click
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, RepoDetailsActivity::class.java)
                intent.putExtra("REPO_ID", repository.id)
                binding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
