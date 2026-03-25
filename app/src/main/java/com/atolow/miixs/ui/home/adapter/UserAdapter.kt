package com.atolow.miixs.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.user.ProfileResponseDto
import com.atolow.miixs.databinding.ItemUserBinding
import java.text.SimpleDateFormat
import java.util.*

class UserAdapter(
    private val onItemClick: ((ProfileResponseDto) -> Unit)? = null
) : ListAdapter<ProfileResponseDto, UserAdapter.UserViewHolder>(UserDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding, onItemClick)
    }
    
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class UserViewHolder(
        private val binding: ItemUserBinding,
        private val onItemClick: ((ProfileResponseDto) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(user: ProfileResponseDto) {
            binding.tvUserName.text = user.name ?: user.username
            
            // 지역과 나이 표시
            val locationText = user.location?.displayName ?: ""
            val ageText = user.age?.let { "${it}살" } ?: ""
            val locationAgeText = when {
                locationText.isNotEmpty() && ageText.isNotEmpty() -> "$locationText $ageText"
                locationText.isNotEmpty() -> locationText
                ageText.isNotEmpty() -> ageText
                else -> ""
            }
            binding.tvLocationAge.text = locationAgeText
            
            // 자기소개 표시
            binding.tvBio.text = user.bio ?: ""
            binding.tvBio.visibility = if (user.bio.isNullOrEmpty()) android.view.View.GONE else android.view.View.VISIBLE
            
            // 가입 시간 표시
            binding.tvTime.text = formatTimeAgo(user.createdAt)
            
            // 프로필 이미지 로드 (profileImageUrls의 첫 번째 이미지 우선 사용, 없으면 profileImageUrl 사용)
            val profileImageUrl = user.profileImageUrls?.firstOrNull() ?: user.profileImageUrl
            if (profileImageUrl.isNullOrEmpty()) {
                binding.ivProfile.setImageResource(R.drawable.bg_profile_circle)
            } else {
                Glide.with(binding.root.context)
                    .load(profileImageUrl)
                    .circleCrop()
                    .placeholder(R.drawable.bg_profile_circle)
                    .error(R.drawable.bg_profile_circle)
                    .into(binding.ivProfile)
            }
            
            binding.root.setOnClickListener {
                onItemClick?.invoke(user) ?: run {
                    val intent = android.content.Intent(binding.root.context, com.atolow.miixs.ui.profile.UserProfileActivity::class.java).apply {
                        putExtra("userId", user.id)
                    }
                    binding.root.context.startActivity(intent)
                }
            }
        }
        
        private fun formatTimeAgo(dateTime: String?): String {
            if (dateTime == null) return "방금전"
            return try {
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val date = sdf.parse(dateTime) ?: return "방금전"
                
                val now = Calendar.getInstance()
                val userDate = Calendar.getInstance().apply { time = date }
                
                val diffInMillis = now.timeInMillis - userDate.timeInMillis
                val diffInDays = diffInMillis / (24 * 60 * 60 * 1000)
                
                when {
                    diffInDays == 0L -> "오늘 가입"
                    diffInDays == 1L -> "어제 가입"
                    diffInDays < 7L -> "${diffInDays}일 전 가입"
                    diffInDays < 30L -> {
                        val weeks = diffInDays / 7
                        "${weeks}주 전 가입"
                    }
                    diffInDays < 365L -> {
                        val months = diffInDays / 30
                        "${months}개월 전 가입"
                    }
                    else -> {
                        val years = diffInDays / 365
                        "${years}년 전 가입"
                    }
                }
            } catch (e: Exception) {
                "방금전"
            }
        }
    }
    
    class UserDiffCallback : DiffUtil.ItemCallback<ProfileResponseDto>() {
        override fun areItemsTheSame(oldItem: ProfileResponseDto, newItem: ProfileResponseDto): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: ProfileResponseDto, newItem: ProfileResponseDto): Boolean {
            return oldItem == newItem
        }
    }
}

