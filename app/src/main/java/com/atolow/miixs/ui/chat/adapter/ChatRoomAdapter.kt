package com.atolow.miixs.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto
import com.atolow.miixs.databinding.ItemChatRoomBinding

class ChatRoomAdapter(
    private val onItemClick: (ChatRoomResponseDto) -> Unit
) : ListAdapter<ChatRoomResponseDto, ChatRoomAdapter.ChatRoomViewHolder>(ChatRoomDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        val binding = ItemChatRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatRoomViewHolder(binding, onItemClick)
    }
    
    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class ChatRoomViewHolder(
        private val binding: ItemChatRoomBinding,
        private val onItemClick: (ChatRoomResponseDto) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(chatRoom: ChatRoomResponseDto) {
            binding.tvOtherUserName.text = chatRoom.otherUserName
            binding.tvLastMessage.text = chatRoom.lastMessage ?: ""
            binding.tvUnreadCount.text = if (chatRoom.unreadCount > 0) {
                chatRoom.unreadCount.toString()
            } else {
                ""
            }
            binding.tvUnreadCount.visibility = if (chatRoom.unreadCount > 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
            
            binding.root.setOnClickListener {
                onItemClick(chatRoom)
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

