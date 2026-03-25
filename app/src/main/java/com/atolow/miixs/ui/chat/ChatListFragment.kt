package com.atolow.miixs.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.atolow.miixs.databinding.FragmentChatListBinding
import com.atolow.miixs.ui.chat.adapter.ChatListItem
import com.atolow.miixs.ui.chat.adapter.ChatRoomListAdapter
import com.atolow.miixs.ui.chat.viewmodel.ChatListViewModel
import com.atolow.miixs.util.ChatNotificationManager
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ChatListFragment : Fragment() {
    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChatListViewModel by viewModels()
    private lateinit var chatRoomAdapter: ChatRoomListAdapter
    
    // 메시지 수신 리스너 (메모리 누수 방지를 위해 변수로 저장)
    private val messageReceivedListener: (Long) -> Unit = { chatRoomId ->
        android.util.Log.d("ChatListFragment", "메시지 수신으로 채팅 목록 새로고침: chatRoomId=$chatRoomId")
        // Fragment가 활성화되어 있을 때만 새로고침
        if (isAdded && view != null) {
            viewModel.loadFavorites()
            viewModel.loadChatRooms()
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        android.util.Log.d("ChatListFragment", "onViewCreated 호출")
        setupRecyclerView()
        setupSwipeRefresh()
        setupObservers()
        setupClickListeners()
        setupMessageReceivedListener()
        
        viewModel.loadFavorites()
        viewModel.loadChatRooms()
    }
    
    private fun setupMessageReceivedListener() {
        // 메시지 수신 시 채팅 목록 새로고침
        ChatNotificationManager.addMessageReceivedListener(messageReceivedListener)
    }
    
    private fun setupClickListeners() {
        binding.btnLeaveAll.setOnClickListener {
            showLeaveAllChatRoomsDialog()
        }
    }
    
    private fun showLeaveAllChatRoomsDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("모든 채팅방 나가기")
            .setMessage("즐겨찾기 제외하고 모든 채팅방을 나갑니다.\n나가시겠습니까?")
            .setPositiveButton("나가기") { _, _ ->
                leaveAllChatRoomsExceptFavorites()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun leaveAllChatRoomsExceptFavorites() {
        viewModel.leaveAllChatRoomsExceptFavorites { result ->
            result.fold(
                onSuccess = { count ->
                    if (count > 0) {
                        android.widget.Toast.makeText(
                            requireContext(),
                            "${count}개의 채팅방을 나갔습니다.",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                        // 목록 새로고침
                        viewModel.loadFavorites()
                        viewModel.loadChatRooms()
                    } else {
                        android.widget.Toast.makeText(
                            requireContext(),
                            "나갈 채팅방이 없습니다.",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                onFailure = { exception ->
                    android.widget.Toast.makeText(
                        requireContext(),
                        exception.message ?: "채팅방 나가기에 실패했습니다.",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
    
    override fun onResume() {
        super.onResume()
        android.util.Log.d("ChatListFragment", "onResume 호출 - 자동 새로고침")
        // Fragment가 다시 보일 때마다 자동으로 새로고침
        viewModel.loadFavorites()
        viewModel.loadChatRooms()
        
        // 채팅방 목록이 업데이트되면 알림 구독도 갱신
        ChatNotificationManager.refreshSubscriptions(requireContext())
    }
    
    private fun setupRecyclerView() {
        val onItemClick: (com.atolow.miixs.data.model.chat.ChatRoomResponseDto) -> Unit = { chatRoom ->
            val intent = Intent(requireContext(), ChatRoomActivity::class.java).apply {
                putExtra("chatRoomId", chatRoom.chatRoomId)
            }
            startActivity(intent)
        }
        
        // 세로 리스트 어댑터
        chatRoomAdapter = ChatRoomListAdapter(onItemClick)
        binding.rvChatRooms.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvChatRooms.adapter = chatRoomAdapter
    }
    
    private fun setupSwipeRefresh() {
        // 하늘색 테마에 맞는 색상 설정
        binding.swipeRefreshLayout.setColorSchemeColors(
            android.graphics.Color.parseColor("#4A90E2"),
            android.graphics.Color.parseColor("#87CEEB"),
            android.graphics.Color.parseColor("#B0D0FF")
        )
        
        binding.swipeRefreshLayout.setOnRefreshListener {
            android.util.Log.d("ChatListFragment", "새로고침 시작")
            // 새로고침 시 즐겨찾기와 채팅방 목록 다시 로드
            viewModel.loadFavorites()
            viewModel.loadChatRooms()
        }
        
        // 로딩 상태에 따라 새로고침 인디케이터 제어
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (!isLoading) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }
    
    private fun setupObservers() {
        // 두 Flow를 combine하여 동시에 업데이트
        lifecycleScope.launch {
            combine(viewModel.favorites, viewModel.chatRooms) { favorites, chatRooms ->
                val items = mutableListOf<ChatListItem>()
                
                // 즐겨찾기 섹션 (항상 표시)
                items.add(ChatListItem.SectionHeader("즐겨찾기"))
                if (favorites.isNotEmpty()) {
                    // 즐겨찾기 정렬 (최신순)
                    val sortedFavorites = favorites.sortedByDescending { 
                        it.lastMessageAt?.let { timestamp ->
                            try {
                                val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault())
                                sdf.parse(timestamp)?.time ?: 0L
                            } catch (e: Exception) {
                                0L
                            }
                        } ?: 0L
                    }
                    sortedFavorites.forEach { items.add(ChatListItem.ChatRoomItem(it)) }
                }
                
                // 일반 채팅 섹션 (항상 표시)
                items.add(ChatListItem.SectionHeader("일반 채팅"))
                val favoriteIds = favorites.map { it.chatRoomId }.toSet()
                val regularChatRooms = chatRooms.filter { it.chatRoomId !in favoriteIds }
                
                if (regularChatRooms.isNotEmpty()) {
                    // 일반 채팅방 정렬 (최신순)
                    val sortedRegular = regularChatRooms.sortedByDescending { 
                        it.lastMessageAt?.let { timestamp ->
                            try {
                                val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault())
                                sdf.parse(timestamp)?.time ?: 0L
                            } catch (e: Exception) {
                                0L
                            }
                        } ?: 0L
                    }
                    sortedRegular.forEach { items.add(ChatListItem.ChatRoomItem(it)) }
                }
                
                android.util.Log.d("ChatListFragment", "채팅방 목록 업데이트: 즐겨찾기 ${favorites.size}개, 일반 ${regularChatRooms.size}개")
                items
            }.collect { items ->
                chatRoomAdapter.submitList(items)
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        // 리스너 제거 (메모리 누수 방지)
        ChatNotificationManager.removeMessageReceivedListener(messageReceivedListener)
        _binding = null
    }
}
