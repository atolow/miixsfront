package com.atolow.miixs.ui.post

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atolow.miixs.R
import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.atolow.miixs.databinding.DialogCreatePostBinding
import com.atolow.miixs.databinding.FragmentPostBinding
import com.atolow.miixs.ui.home.viewmodel.HomeViewModel
import com.atolow.miixs.ui.post.adapter.PostListAdapter
import com.atolow.miixs.ui.post.viewmodel.PostViewModel
import kotlinx.coroutines.launch

class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val postViewModel: PostViewModel by viewModels()
    private lateinit var postAdapter: PostListAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupSwipeRefresh()
        setupClickListeners()
        setupObservers()
        viewModel.loadPosts()
    }
    
    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadPosts()
        }
    }
    
    override fun onResume() {
        super.onResume()
        // 게시물 작성 화면에서 돌아왔을 때 목록 새로고침
        viewModel.loadPosts()
    }
    
    private fun setupRecyclerView() {
        postAdapter = PostListAdapter()
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = postAdapter
    }
    
    private fun setupClickListeners() {
        // 연필 버튼 클릭 시 게시물 작성 화면으로 이동
        binding.ivEdit.setOnClickListener {
            showCreatePostDialog()
        }
        
        // 필터 클릭 리스너
        binding.tvLocationFilter.setOnClickListener { view ->
            android.util.Log.d("PostFragment", "지역 필터 클릭됨!!!")
            try {
                Toast.makeText(requireContext(), "지역 필터 클릭됨", Toast.LENGTH_SHORT).show()
                showLocationFilterBottomSheet()
            } catch (e: Exception) {
                android.util.Log.e("PostFragment", "지역 필터 클릭 처리 중 오류", e)
                e.printStackTrace()
            }
        }
        
        binding.tvGenderFilter.setOnClickListener { view ->
            android.util.Log.d("PostFragment", "성별 필터 클릭됨!!!")
            try {
                Toast.makeText(requireContext(), "성별 필터 클릭됨", Toast.LENGTH_SHORT).show()
                showGenderFilterBottomSheet()
            } catch (e: Exception) {
                android.util.Log.e("PostFragment", "성별 필터 클릭 처리 중 오류", e)
                e.printStackTrace()
            }
        }
        
        binding.tvAgeFilter.setOnClickListener { view ->
            android.util.Log.d("PostFragment", "나이 필터 클릭됨!!!")
            try {
                Toast.makeText(requireContext(), "나이 필터 클릭됨", Toast.LENGTH_SHORT).show()
                showAgeFilterBottomSheet()
            } catch (e: Exception) {
                android.util.Log.e("PostFragment", "나이 필터 클릭 처리 중 오류", e)
                e.printStackTrace()
            }
        }
    }
    
    private fun showLocationFilterBottomSheet() {
        if (!isAdded || !isResumed) {
            return
        }
        
        val activity = activity ?: return
        
        try {
            val locations = listOf("전체 지역") + Location.values().map { it.displayName }
            val items = locations.toTypedArray()
            AlertDialog.Builder(activity)
                .setTitle("지역 선택")
                .setItems(items) { dialog, which ->
                    val selectedLocation = if (which == 0) null else Location.values()[which - 1]
                    viewModel.setLocationFilter(selectedLocation)
                    if (isAdded) {
                        binding.tvLocationFilter.text = selectedLocation?.displayName ?: "전체 지역"
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("취소", null)
                .show()
        } catch (e: Exception) {
            android.util.Log.e("PostFragment", "지역 필터 다이얼로그 표시 실패", e)
            Toast.makeText(activity, "오류: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun showGenderFilterBottomSheet() {
        if (!isAdded || !isResumed) {
            return
        }
        
        val activity = activity ?: return
        
        try {
            val genders = listOf("전체 성별", "남성", "여성")
            val items = genders.toTypedArray()
            
            AlertDialog.Builder(activity)
                .setTitle("성별 선택")
                .setItems(items) { dialog, which ->
                    val selectedGender = when (which) {
                        0 -> null
                        1 -> Gender.MALE
                        2 -> Gender.FEMALE
                        else -> null
                    }
                    viewModel.setGenderFilter(selectedGender)
                    if (isAdded) {
                        binding.tvGenderFilter.text = when (selectedGender) {
                            Gender.MALE -> "남성"
                            Gender.FEMALE -> "여성"
                            else -> "전체 성별"
                        }
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("취소", null)
                .show()
        } catch (e: Exception) {
            android.util.Log.e("PostFragment", "성별 필터 다이얼로그 표시 실패", e)
            Toast.makeText(activity, "오류: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun showAgeFilterBottomSheet() {
        if (!isAdded || !isResumed) {
            return
        }
        
        val activity = activity ?: return
        
        try {
            val ageRanges = listOf("전체 나이", "20~30", "30~40", "40~50", "50~60")
            val items = ageRanges.toTypedArray()
            
            AlertDialog.Builder(activity)
                .setTitle("나이 선택")
                .setItems(items) { dialog, which ->
                    val (minAge, maxAge) = when (which) {
                        0 -> Pair(null, null)
                        1 -> Pair(20, 30)
                        2 -> Pair(30, 40)
                        3 -> Pair(40, 50)
                        4 -> Pair(50, 60)
                        else -> Pair(null, null)
                    }
                    viewModel.setAgeFilter(minAge, maxAge)
                    if (isAdded) {
                        binding.tvAgeFilter.text = ageRanges[which]
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("취소", null)
                .show()
        } catch (e: Exception) {
            android.util.Log.e("PostFragment", "나이 필터 다이얼로그 표시 실패", e)
            Toast.makeText(activity, "오류: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.posts.collect { posts ->
                postAdapter.submitList(posts)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (!isLoading) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
        
    }
    
    private fun showCreatePostDialog() {
        val intent = Intent(requireContext(), CreatePostActivity::class.java)
        startActivity(intent)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

