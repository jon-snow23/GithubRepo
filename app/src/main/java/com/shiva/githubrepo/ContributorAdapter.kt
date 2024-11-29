package com.shiva.githubrepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shiva.githubrepo.databinding.ContributorItemBinding

class ContributorAdapter : ListAdapter<Contributor, ContributorAdapter.ContributorViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val binding = ContributorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContributorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val contributor = getItem(position)
        contributor?.let {
            holder.bind(it)
        }
    }

    inner class ContributorViewHolder(private val binding: ContributorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contributor: Contributor) {
            binding.contributorNameTextView.text = contributor.login
            Glide.with(binding.root.context).load(contributor.avatar_url).into(binding.contributorAvatarImageView)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contributor>() {
            override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor): Boolean {
                return oldItem == newItem
            }
        }
    }
}
