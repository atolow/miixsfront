package com.atolow.miixs.ui.post.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.post.PostResponseDto
import com.atolow.miixs.databinding.ItemPostBinding
import com.atolow.miixs.ui.post.PostDetailActivity

class PostListAdapter(
    private val onItemClick: ((PostResponseDto) -> Unit)? = null
) : ListAdapter<PostResponseDto, PostListAdapter.PostViewHolder>(PostDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding, onItemClick)
    }
    
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class PostViewHolder(
        private val binding: ItemPostBinding,
        private val onItemClick: ((PostResponseDto) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(post: PostResponseDto) {
            binding.tvAuthorName.text = post.authorName
            
            // 탈퇴한 회원인지 확인 (닉네임이 "탈퇴된 회원입니다"인 경우)
            val isDeactivated = post.authorName == "탈퇴된 회원입니다"
            
            // 프로필 이미지 로드 (탈퇴한 회원이거나 null이거나 빈 문자열이면 기본 이미지 표시)
            if (isDeactivated || post.authorProfileImageUrl.isNullOrEmpty()) {
                binding.ivProfile.setImageResource(R.drawable.bg_profile_circle)
            } else {
                Glide.with(binding.root.context)
                    .load(post.authorProfileImageUrl)
                    .circleCrop()
                    .placeholder(R.drawable.bg_profile_circle)
                    .error(R.drawable.bg_profile_circle)
                    .into(binding.ivProfile)
            }
            
            // 지역과 나이 표시
            val locationText = post.authorLocation?.displayName ?: ""
            val ageText = post.authorAge?.let { "${it}살" } ?: ""
            val locationAgeText = when {
                locationText.isNotEmpty() && ageText.isNotEmpty() -> "$locationText $ageText"
                locationText.isNotEmpty() -> locationText
                ageText.isNotEmpty() -> ageText
                else -> ""
            }
            binding.tvLocationAge.text = locationAgeText
            
            binding.tvContent.text = post.content
            binding.tvTime.text = formatTimeAgo(post.createdAt)
            
            // 프로필 이미지 클릭 시 사용자 프로필 화면으로 이동
            binding.ivProfile.setOnClickListener {
                if (!isDeactivated) {
                    val intent = Intent(binding.root.context, com.atolow.miixs.ui.profile.UserProfileActivity::class.java).apply {
                        putExtra("userId", post.authorId)
                    }
                    binding.root.context.startActivity(intent)
                }
            }
            
            // 작성자 이름 클릭 시에도 사용자 프로필 화면으로 이동
            binding.tvAuthorName.setOnClickListener {
                if (!isDeactivated) {
                    val intent = Intent(binding.root.context, com.atolow.miixs.ui.profile.UserProfileActivity::class.java).apply {
                        putExtra("userId", post.authorId)
                    }
                    binding.root.context.startActivity(intent)
                }
            }
            
            // 게시물 박스 클릭 시 유저 프로필로 이동 (홈과 동일하게)
            binding.root.setOnClickListener {
                if (!isDeactivated) {
                    val intent = Intent(binding.root.context, com.atolow.miixs.ui.profile.UserProfileActivity::class.java).apply {
                        putExtra("userId", post.authorId)
                    }
                    binding.root.context.startActivity(intent)
                }
            }
        }
        
        private fun formatTimeAgo(dateTime: String?): String {
            if (dateTime == null) return "방금전"
            return try {
                // 간단하게 "방금전"으로 표시 (나중에 실제 시간 계산 로직 추가 가능)
                "방금전"
            } catch (e: Exception) {
                "방금전"
            }
        }
    }
    
    class PostDiffCallback : DiffUtil.ItemCallback<PostResponseDto>() {
        override fun areItemsTheSame(oldItem: PostResponseDto, newItem: PostResponseDto): Boolean {
            return oldItem.postId == newItem.postId
        }
        
        override fun areContentsTheSame(oldItem: PostResponseDto, newItem: PostResponseDto): Boolean {
            return oldItem == newItem
        }
    }
}

