package com.atolow.miixs.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.atolow.miixs.databinding.ActivityBlockedUsersBinding
import com.atolow.miixs.ui.profile.adapter.BlockedUserAdapter
import com.atolow.miixs.ui.profile.viewmodel.BlockedUsersViewModel
import kotlinx.coroutines.launch

class BlockedUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlockedUsersBinding
    private lateinit var viewModel: BlockedUsersViewModel
    private lateinit var adapter: BlockedUserAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlockedUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[BlockedUsersViewModel::class.java]
        
        setupToolbar()
        setupRecyclerView()
        setupSwipeToUnblock()
        setupObservers()
        
        // 초기 상태 설정: 빈 상태 메시지 표시, RecyclerView 숨김
        binding.tvEmptyState.visibility = View.VISIBLE
        binding.rvBlockedUsers.visibility = View.GONE
        
        viewModel.loadBlockedUsers()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = BlockedUserAdapter(
            onUnblockClick = { blockedUser ->
                // 차단 해제 버튼 클릭 시
                adapter.clearSelection()
                viewModel.unblockUser(blockedUser.blockedUserId)
            },
            onItemClick = {
                // 다른 아이템 클릭 시 선택 해제
                adapter.clearSelection()
            }
        )
        binding.rvBlockedUsers.layoutManager = LinearLayoutManager(this).apply {
            isItemPrefetchEnabled = true
        }
        binding.rvBlockedUsers.adapter = adapter
        binding.rvBlockedUsers.setHasFixedSize(false)
        android.util.Log.d("BlockedUsersActivity", "RecyclerView 설정 완료")
    }
    
    private fun setupSwipeToUnblock() {
        // 스와이프 기능 제거 - 길게 누르기로 대체
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.blockedUsers.collect { users ->
                android.util.Log.d("BlockedUsersActivity", "차단한 회원 목록 업데이트: ${users.size}명")
                // 빈 상태 표시/숨김
                if (users.isEmpty()) {
                    android.util.Log.d("BlockedUsersActivity", "빈 상태 메시지 표시")
                    binding.tvEmptyState.visibility = View.VISIBLE
                    binding.rvBlockedUsers.visibility = View.GONE
                    adapter.submitList(emptyList())
                } else {
                    android.util.Log.d("BlockedUsersActivity", "차단한 회원 목록 표시: ${users.size}명")
                    android.util.Log.d("BlockedUsersActivity", "첫 번째 사용자: ${users.firstOrNull()?.blockedUsername}")
                    binding.tvEmptyState.visibility = View.GONE
                    binding.rvBlockedUsers.visibility = View.VISIBLE
                    adapter.submitList(users) {
                        // 리스트 업데이트 후 확인
                        android.util.Log.d("BlockedUsersActivity", "Adapter 아이템 수: ${adapter.itemCount}")
                        // RecyclerView 강제 새로고침
                        binding.rvBlockedUsers.post {
                            binding.rvBlockedUsers.invalidate()
                            binding.rvBlockedUsers.requestLayout()
                            // Adapter가 제대로 업데이트되었는지 확인
                            if (adapter.itemCount > 0) {
                                android.util.Log.d("BlockedUsersActivity", "RecyclerView 새로고침 완료, 아이템 수: ${adapter.itemCount}")
                            }
                        }
                    }
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.unblockResult.collect { result ->
                result?.let { res ->
                    res.onSuccess {
                        android.util.Log.d("BlockedUsersActivity", "차단 해제 성공")
                        Toast.makeText(this@BlockedUsersActivity, "차단이 해제되었습니다", Toast.LENGTH_SHORT).show()
                        // 목록 자동 새로고침 (ViewModel에서 이미 처리됨)
                    }.onFailure { exception ->
                        android.util.Log.e("BlockedUsersActivity", "차단 해제 실패", exception)
                        Toast.makeText(this@BlockedUsersActivity, exception.message ?: "차단 해제에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                    viewModel.clearUnblockResult()
                }
            }
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

