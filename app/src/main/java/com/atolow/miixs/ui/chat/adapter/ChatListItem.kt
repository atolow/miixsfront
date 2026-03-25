package com.atolow.miixs.ui.chat.adapter

import com.atolow.miixs.data.model.chat.ChatRoomResponseDto

sealed class ChatListItem {
    data class SectionHeader(val title: String) : ChatListItem()
    data class ChatRoomItem(val chatRoom: ChatRoomResponseDto) : ChatListItem()
}

