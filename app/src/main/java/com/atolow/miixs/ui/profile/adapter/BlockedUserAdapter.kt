package com.atolow.miixs.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.user.BlockedUserDto
import com.atolow.miixs.databinding.ItemBlockedUserBinding

class BlockedUserAdapter(
    private val onUnblockClick: (BlockedUserDto) -> Unit,
    private val onItemClick: (() -> Unit)? = null
) : ListAdapter<BlockedUserDto, BlockedUserAdapter.ViewHolder>(DiffCallback()) {
    
    private var selectedPosition = -1
    
    class ViewHolder(
        private val binding: ItemBlockedUserBinding,
        private val onUnblockClick: (BlockedUserDto) -> Unit,
        private val onItemClick: (() -> Unit)?,
        private val adapter: BlockedUserAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(user: BlockedUserDto, position: Int) {
            binding.tvUsername.text = user.blockedUsername
            binding.tvBlockedInfo.text = "차단된 사용자"
            
            // 프로필 이미지 로드 (null이거나 빈 문자열이면 기본 이미지 표시)
            if (user.profileImageUrl.isNullOrEmpty()) {
                binding.ivProfile.setImageResource(R.drawable.bg_profile_circle)
            } else {
                Glide.with(binding.root.context)
                    .load(user.profileImageUrl)
                    .placeholder(R.drawable.bg_profile_circle)
                    .error(R.drawable.bg_profile_circle)
                    .circleCrop()
                    .into(binding.ivProfile)
            }
            
            val isSelected = adapter.selectedPosition == position
            android.util.Log.d("BlockedUserAdapter", "bind: position=$position, isSelected=$isSelected, selectedPosition=${adapter.selectedPosition}")
            
            // 차단 해제 버튼 표시/숨김
            binding.cardSwipeAction.visibility = if (isSelected) {
                android.util.Log.d("BlockedUserAdapter", "버튼 VISIBLE로 설정: position=$position")
                android.view.View.VISIBLE
            } else {
                android.util.Log.d("BlockedUserAdapter", "버튼 GONE으로 설정: position=$position")
                android.view.View.GONE
            }
            
            // 강제로 레이아웃 업데이트
            binding.cardSwipeAction.requestLayout()
            binding.cardSwipeAction.invalidate()
            
            // 차단해제 버튼 표시 함수
            fun showUnblockButton() {
                android.util.Log.d("BlockedUserAdapter", "버튼 표시: position=$position")
                // 다른 아이템의 선택 해제 (먼저 호출)
                val oldPosition = adapter.selectedPosition
                if (oldPosition != -1) {
                    adapter.clearSelection()
                }
                // 현재 아이템 선택
                adapter.setSelectedPosition(position)
            }
            
            // 차단 해제 버튼 클릭 리스너
            binding.cardSwipeAction.setOnClickListener { view ->
                android.util.Log.d("BlockedUserAdapter", "차단해제 버튼 클릭: position=$position, userId=${user.blockedUserId}")
                view.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
                adapter.clearSelection()
                onUnblockClick(user)
            }
            
            // 카드 클릭 리스너 (차단해제 버튼 표시/숨김 토글)
            binding.cardContent.setOnClickListener {
                android.util.Log.d("BlockedUserAdapter", "카드 클릭: position=$position, isSelected=$isSelected, selectedPosition=${adapter.selectedPosition}")
                if (isSelected) {
                    // 이미 선택된 경우 버튼 숨기기
                    android.util.Log.d("BlockedUserAdapter", "버튼 숨기기")
                    adapter.clearSelection()
                } else {
                    // 선택되지 않은 경우 버튼 표시
                    showUnblockButton()
                }
            }
            
            // 카드 길게 누르기 리스너 (차단해제 버튼 표시)
            binding.cardContent.setOnLongClickListener {
                android.util.Log.d("BlockedUserAdapter", "카드 길게 누르기: position=$position")
                if (!isSelected) {
                    showUnblockButton()
                }
                true
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBlockedUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onUnblockClick, onItemClick, this)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
    
    fun setSelectedPosition(position: Int) {
        android.util.Log.d("BlockedUserAdapter", "setSelectedPosition: $position (현재: $selectedPosition)")
        val oldPosition = selectedPosition
        selectedPosition = position
        if (oldPosition != -1 && oldPosition < itemCount) {
            android.util.Log.d("BlockedUserAdapter", "이전 아이템 업데이트: $oldPosition")
            notifyItemChanged(oldPosition)
        }
        if (selectedPosition != -1 && selectedPosition < itemCount) {
            android.util.Log.d("BlockedUserAdapter", "새 아이템 업데이트: $selectedPosition")
            notifyItemChanged(selectedPosition)
        }
    }
    
    fun clearSelection() {
        android.util.Log.d("BlockedUserAdapter", "clearSelection (현재: $selectedPosition)")
        val oldPosition = selectedPosition
        selectedPosition = -1
        if (oldPosition != -1 && oldPosition < itemCount) {
            android.util.Log.d("BlockedUserAdapter", "선택 해제 아이템 업데이트: $oldPosition")
            notifyItemChanged(oldPosition)
        }
    }
    
    class DiffCallback : DiffUtil.ItemCallback<BlockedUserDto>() {
        override fun areItemsTheSame(oldItem: BlockedUserDto, newItem: BlockedUserDto): Boolean {
            return oldItem.blockedUserId == newItem.blockedUserId
        }
        
        override fun areContentsTheSame(oldItem: BlockedUserDto, newItem: BlockedUserDto): Boolean {
            return oldItem == newItem
        }
    }
}
