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
import com.atolow.miixs.databinding.ItemChatRoomListBinding
import com.atolow.miixs.databinding.ItemChatSectionHeaderBinding
import java.text.SimpleDateFormat
import java.util.*

class ChatRoomListAdapter(
    private val onItemClick: (ChatRoomResponseDto) -> Unit
) : ListAdapter<ChatListItem, RecyclerView.ViewHolder>(ChatListItemDiffCallback()) {
    
    companion object {
        private const val VIEW_TYPE_SECTION_HEADER = 0
        private const val VIEW_TYPE_CHAT_ROOM = 1
    }
    
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ChatListItem.SectionHeader -> VIEW_TYPE_SECTION_HEADER
            is ChatListItem.ChatRoomItem -> VIEW_TYPE_CHAT_ROOM
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SECTION_HEADER -> {
                val binding = ItemChatSectionHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SectionHeaderViewHolder(binding)
            }
            VIEW_TYPE_CHAT_ROOM -> {
                val binding = ItemChatRoomListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ChatRoomListViewHolder(binding, onItemClick)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ChatListItem.SectionHeader -> {
                (holder as SectionHeaderViewHolder).bind(item.title)
            }
            is ChatListItem.ChatRoomItem -> {
                (holder as ChatRoomListViewHolder).bind(item.chatRoom)
            }
        }
    }
    
    class SectionHeaderViewHolder(
        private val binding: ItemChatSectionHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(title: String) {
            binding.root.text = title
        }
    }
    
    class ChatRoomListViewHolder(
        private val binding: ItemChatRoomListBinding,
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
            
            // 타임스탬프 표시
            binding.tvTimestamp.text = formatTimestamp(chatRoom.lastMessageAt)
            
            // 탈퇴한 회원인지 확인 (닉네임이 "탈퇴된 회원입니다"인 경우)
            val isDeactivated = chatRoom.otherUserName == "탈퇴된 회원입니다"
            
            // 프로필 이미지 로드
            val profileImageUrl = chatRoom.otherUserProfileImageUrl
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
        
        private fun loadProfileImage(imageUrl: String?, isDeactivated: Boolean = false) {
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
        
        private fun formatTimestamp(timestamp: String?): String {
            if (timestamp == null) return ""
            
            return try {
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val date = sdf.parse(timestamp) ?: return ""
                
                val now = Calendar.getInstance()
                val messageDate = Calendar.getInstance().apply { time = date }
                
                val diffInMillis = now.timeInMillis - messageDate.timeInMillis
                val diffInDays = diffInMillis / (24 * 60 * 60 * 1000)
                
                when {
                    diffInDays == 0L -> {
                        // 오늘인 경우 시간만 표시
                        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                        timeFormat.format(date)
                    }
                    diffInDays == 1L -> "어제"
                    diffInDays < 7L -> "${diffInDays}일 전"
                    else -> {
                        // 일주일 이상 지난 경우 날짜 표시
                        val dateFormat = SimpleDateFormat("M월 d일", Locale.getDefault())
                        dateFormat.format(date)
                    }
                }
            } catch (e: Exception) {
                ""
            }
        }
    }
    
    class ChatListItemDiffCallback : DiffUtil.ItemCallback<ChatListItem>() {
        override fun areItemsTheSame(oldItem: ChatListItem, newItem: ChatListItem): Boolean {
            return when {
                oldItem is ChatListItem.SectionHeader && newItem is ChatListItem.SectionHeader -> 
                    oldItem.title == newItem.title
                oldItem is ChatListItem.ChatRoomItem && newItem is ChatListItem.ChatRoomItem -> 
                    oldItem.chatRoom.chatRoomId == newItem.chatRoom.chatRoomId
                else -> false
            }
        }
        
        override fun areContentsTheSame(oldItem: ChatListItem, newItem: ChatListItem): Boolean {
            return oldItem == newItem
        }
    }
}

