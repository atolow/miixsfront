package com.atolow.miixs.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.databinding.ItemProfileImageBinding

class ProfileImagePagerAdapter : ListAdapter<String, ProfileImagePagerAdapter.ImageViewHolder>(ImageDiffCallback()) {
    
    init {
        // 초기화 시 기본 빈 이미지 표시
        submitList(listOf(""))
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemProfileImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (position < currentList.size) {
            holder.bind(getItem(position))
        }
    }
    
    fun updateImages(newImageUrls: List<String>) {
        val listToSubmit = if (newImageUrls.isEmpty()) {
            listOf("")
        } else {
            newImageUrls
        }
        submitList(listToSubmit)
    }
    
    class ImageViewHolder(
        private val binding: ItemProfileImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(imageUrl: String) {
            if (imageUrl.isEmpty()) {
                // 기본 이미지 표시
                binding.ivProfileImage.setImageResource(R.drawable.bg_profile_circle)
            } else {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .fitCenter()
                    .placeholder(R.drawable.bg_profile_circle)
                    .error(R.drawable.bg_profile_circle)
                    .into(binding.ivProfileImage)
            }
        }
    }
    
    class ImageDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}

