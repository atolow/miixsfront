package com.atolow.miixs.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.chat.MessageResponseDto
import com.atolow.miixs.databinding.ItemMessageSentBinding
import com.atolow.miixs.databinding.ItemMessageReceivedBinding
import com.atolow.miixs.databinding.ItemMessageDateBinding
import com.atolow.miixs.databinding.ItemMessageSystemBinding
import java.text.SimpleDateFormat
import java.util.Locale

sealed class ChatItem {
    data class Message(val message: MessageResponseDto) : ChatItem()
    data class Date(val dateString: String) : ChatItem()
}

class MessageAdapter(
    private val currentUserId: Long,
    private var otherUserProfileImageUrl: String? = null,
    private var otherUserName: String? = null
) : ListAdapter<ChatItem, RecyclerView.ViewHolder>(ChatItemDiffCallback()) {
    
    fun updateOtherUserProfile(profileImageUrl: String?, userName: String?) {
        otherUserProfileImageUrl = profileImageUrl
        otherUserName = userName
        notifyDataSetChanged()
    }
    
    // ReceivedMessageViewHolder에서 접근할 수 있도록 getter 제공
    fun getOtherUserProfileImageUrl(): String? = otherUserProfileImageUrl
    fun getOtherUserName(): String? = otherUserName
    
    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
        private const val VIEW_TYPE_DATE = 3
        private const val VIEW_TYPE_SYSTEM = 4
    }
    
    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is ChatItem.Message -> {
                if (item.message.messageType.name == "SYSTEM") {
                    VIEW_TYPE_SYSTEM
                } else if (item.message.senderId == currentUserId) {
                    VIEW_TYPE_SENT
                } else {
                    VIEW_TYPE_RECEIVED
                }
            }
            is ChatItem.Date -> VIEW_TYPE_DATE
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SENT -> {
                val binding = ItemMessageSentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SentMessageViewHolder(binding)
            }
            VIEW_TYPE_RECEIVED -> {
                val binding = ItemMessageReceivedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReceivedMessageViewHolder(binding, this)
            }
            VIEW_TYPE_DATE -> {
                val binding = ItemMessageDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DateViewHolder(binding)
            }
            VIEW_TYPE_SYSTEM -> {
                val binding = ItemMessageSystemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SystemMessageViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ChatItem.Message -> {
                when (holder) {
                    is SentMessageViewHolder -> holder.bind(item.message)
                    is ReceivedMessageViewHolder -> holder.bind(item.message)
                    is SystemMessageViewHolder -> holder.bind(item.message)
                }
            }
            is ChatItem.Date -> {
                if (holder is DateViewHolder) {
                    holder.bind(item.dateString)
                }
            }
        }
    }
    
    fun submitMessages(messages: List<MessageResponseDto>) {
        val items = mutableListOf<ChatItem>()
        var previousDate: String? = null
        
        messages.forEach { message ->
            val messageDate = formatDate(message.createdAt)
            if (messageDate != null && messageDate.isNotEmpty() && messageDate != previousDate) {
                items.add(ChatItem.Date(messageDate))
                previousDate = messageDate
            }
            items.add(ChatItem.Message(message))
        }
        
        submitList(items)
    }
    
    private fun formatDate(dateString: String?): String {
        if (dateString == null) return ""
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy년 MM월 dd일 EEEE", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) } ?: ""
        } catch (e: Exception) {
            dateString.substring(0, 10) // 기본 형식
        }
    }
    
    class SentMessageViewHolder(
        private val binding: ItemMessageSentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageResponseDto) {
            // 메시지 타입에 따라 텍스트 또는 이미지 표시
            when (message.messageType) {
                com.atolow.miixs.data.model.MessageType.IMAGE -> {
                    // 이미지 메시지
                    binding.tvMessage.visibility = android.view.View.GONE
                    binding.ivMessageImage.visibility = android.view.View.VISIBLE
                    
                    // 이미지 로드
                    Glide.with(binding.root.context)
                        .load(message.content) // content에 이미지 URL이 저장되어 있음
                        .centerCrop()
                        .placeholder(R.drawable.bg_profile_circle)
                        .error(R.drawable.bg_profile_circle)
                        .into(binding.ivMessageImage)
                }
                else -> {
                    // 텍스트 메시지
                    binding.tvMessage.visibility = android.view.View.VISIBLE
                    binding.ivMessageImage.visibility = android.view.View.GONE
                    binding.tvMessage.text = message.content
                }
            }
            
            binding.tvTime.text = formatTime(message.createdAt)
        }
        
        private fun formatTime(timeString: String?): String {
            if (timeString == null) return ""
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("a hh:mm", Locale.getDefault())
                val date = inputFormat.parse(timeString)
                date?.let { outputFormat.format(it) } ?: timeString.substring(11, 16)
            } catch (e: Exception) {
                try {
                    timeString.substring(11, 16) // HH:mm 형식
                } catch (ex: Exception) {
                    timeString
                }
            }
        }
    }
    
    class ReceivedMessageViewHolder(
        private val binding: ItemMessageReceivedBinding,
        private val adapter: MessageAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageResponseDto) {
            // 메시지 타입에 따라 텍스트 또는 이미지 표시
            when (message.messageType) {
                com.atolow.miixs.data.model.MessageType.IMAGE -> {
                    // 이미지 메시지
                    binding.tvMessage.visibility = android.view.View.GONE
                    binding.ivMessageImage.visibility = android.view.View.VISIBLE
                    
                    // 이미지 로드
                    Glide.with(binding.root.context)
                        .load(message.content) // content에 이미지 URL이 저장되어 있음
                        .centerCrop()
                        .placeholder(R.drawable.bg_profile_circle)
                        .error(R.drawable.bg_profile_circle)
                        .into(binding.ivMessageImage)
                }
                else -> {
                    // 텍스트 메시지
                    binding.tvMessage.visibility = android.view.View.VISIBLE
                    binding.ivMessageImage.visibility = android.view.View.GONE
                    binding.tvMessage.text = message.content
                }
            }
            
            binding.tvTime.text = formatTime(message.createdAt)
            
            // 탈퇴한 회원인지 확인 (닉네임이 "탈퇴된 회원입니다"인 경우)
            val otherUserName = adapter.getOtherUserName()
            val isDeactivated = otherUserName == "탈퇴된 회원입니다"
            
            // 프로필 이미지 로드 (탈퇴한 회원이거나 null이거나 빈 문자열이면 기본 이미지 표시)
            val profileImageUrl = adapter.getOtherUserProfileImageUrl()
            if (isDeactivated || profileImageUrl.isNullOrEmpty()) {
                binding.ivProfile.setImageResource(R.drawable.bg_profile_circle)
            } else {
                Glide.with(binding.root.context)
                    .load(profileImageUrl)
                    .circleCrop()
                    .placeholder(R.drawable.bg_profile_circle)
                    .error(R.drawable.bg_profile_circle)
                    .into(binding.ivProfile)
            }
        }
        
        private fun formatTime(timeString: String?): String {
            if (timeString == null) return ""
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("a hh:mm", Locale.getDefault())
                val date = inputFormat.parse(timeString)
                date?.let { outputFormat.format(it) } ?: timeString.substring(11, 16)
            } catch (e: Exception) {
                try {
                    timeString.substring(11, 16) // HH:mm 형식
                } catch (ex: Exception) {
                    timeString
                }
            }
        }
    }
    
    class DateViewHolder(
        private val binding: ItemMessageDateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dateString: String) {
            binding.tvDate.text = dateString
        }
    }
    
    class SystemMessageViewHolder(
        private val binding: ItemMessageSystemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageResponseDto) {
            binding.tvSystemMessage.text = message.content
        }
    }
    
    class ChatItemDiffCallback : DiffUtil.ItemCallback<ChatItem>() {
        override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return when {
                oldItem is ChatItem.Message && newItem is ChatItem.Message -> {
                    oldItem.message.messageId == newItem.message.messageId
                }
                oldItem is ChatItem.Date && newItem is ChatItem.Date -> {
                    oldItem.dateString == newItem.dateString
                }
                else -> false
            }
        }
        
        override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem == newItem
        }
    }
}

