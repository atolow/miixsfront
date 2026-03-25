package com.atolow.miixs.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto
import com.atolow.miixs.databinding.ItemChatRoomFavoriteBinding

class FavoriteChatRoomAdapter(
    private val onItemClick: (ChatRoomResponseDto) -> Unit
) : ListAdapter<ChatRoomResponseDto, FavoriteChatRoomAdapter.FavoriteChatRoomViewHolder>(ChatRoomDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteChatRoomViewHolder {
        android.util.Log.d("FavoriteChatRoomAdapter", "onCreateViewHolder 호출")
        val binding = ItemChatRoomFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteChatRoomViewHolder(binding, onItemClick)
    }
    
    override fun onBindViewHolder(holder: FavoriteChatRoomViewHolder, position: Int) {
        val item = getItem(position)
        android.util.Log.d("FavoriteChatRoomAdapter", "onBindViewHolder 호출: position=$position, chatRoomId=${item.chatRoomId}, username=${item.otherUserName}")
        holder.bind(item)
    }
    
    override fun getItemCount(): Int {
        val count = super.getItemCount()
        android.util.Log.d("FavoriteChatRoomAdapter", "getItemCount: $count")
        return count
    }
    
    class FavoriteChatRoomViewHolder(
        private val binding: ItemChatRoomFavoriteBinding,
        private val onItemClick: (ChatRoomResponseDto) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(chatRoom: ChatRoomResponseDto) {
            // 사용자 이름 표시
            binding.tvOtherUserName.text = chatRoom.otherUserName
            
            // 마지막 메시지 표시
            val lastMessage = chatRoom.lastMessage?.takeIf { it.isNotBlank() } ?: "메시지가 없습니다"
            // 이미지 URL인 경우 "이미지를 보냈습니다."로 표시
            val displayMessage = if (isImageUrl(lastMessage)) {
                "이미지를 보냈습니다."
            } else {
                lastMessage
            }
            binding.tvLastMessage.text = displayMessage
            
            // 탈퇴한 회원인지 확인 (닉네임이 "탈퇴된 회원입니다"인 경우)
            val isDeactivated = chatRoom.otherUserName == "탈퇴된 회원입니다"
            
            // 프로필 이미지 로드 (백엔드에서 받은 URL 사용)
            val profileImageUrl = chatRoom.otherUserProfileImageUrl
            android.util.Log.d("FavoriteChatRoomAdapter", "프로필 이미지 로드: username=${chatRoom.otherUserName}, imageUrl=$profileImageUrl")
            loadProfileImage(profileImageUrl, isDeactivated)
            
            // 읽지 않은 메시지 수 표시
            if (chatRoom.unreadCount > 0) {
                binding.tvUnreadCount.text = if (chatRoom.unreadCount > 99) "99+" else chatRoom.unreadCount.toString()
                binding.tvUnreadCount.visibility = View.VISIBLE
            } else {
                binding.tvUnreadCount.visibility = View.GONE
            }
            
            binding.root.setOnClickListener {
                onItemClick(chatRoom)
            }
        }
        
        private fun isImageUrl(text: String): Boolean {
            // S3 URL인지 확인
            if (text.startsWith("https://miixs-storage.s3") || 
                text.startsWith("http://miixs-storage.s3") ||
                text.startsWith("https://") && (text.contains(".jpg") || text.contains(".jpeg") || 
                text.contains(".png") || text.contains(".gif") || text.contains(".webp"))) {
                return true
            }
            return false
        }
        
        private fun loadProfileImage(imageUrl: String?, isDeactivated: Boolean = false) {
            android.util.Log.d("FavoriteChatRoomAdapter", "Glide로 이미지 로드: $imageUrl")
            // 탈퇴한 회원이거나 null이거나 빈 문자열이면 기본 이미지 표시
            if (isDeactivated || imageUrl.isNullOrEmpty()) {
                binding.ivProfileImage.setImageResource(R.drawable.bg_profile_circle)
            } else {
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .circleCrop()
                    .placeholder(R.drawable.bg_profile_circle)
                    .error(R.drawable.bg_profile_circle)
                    .into(binding.ivProfileImage)
            }
        }
    }
    
    class ChatRoomDiffCallback : DiffUtil.ItemCallback<ChatRoomResponseDto>() {
        override fun areItemsTheSame(oldItem: ChatRoomResponseDto, newItem: ChatRoomResponseDto): Boolean {
            return oldItem.chatRoomId == newItem.chatRoomId
        }
        
        override fun areContentsTheSame(oldItem: ChatRoomResponseDto, newItem: ChatRoomResponseDto): Boolean {
            return oldItem == newItem
        }
    }
}

