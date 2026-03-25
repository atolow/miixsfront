package com.atolow.miixs.ui.payment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.databinding.ActivityPaymentHistoryBinding
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.payment.adapter.PaymentHistoryAdapter
import com.atolow.miixs.ui.payment.viewmodel.PaymentHistoryViewModel
import kotlinx.coroutines.launch

class PaymentHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentHistoryBinding
    private val viewModel: PaymentHistoryViewModel by viewModels()
    private lateinit var paymentHistoryAdapter: PaymentHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 토큰이 없으면 로그인 화면으로 이동
        if (!TokenManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = ActivityPaymentHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupSwipeRefresh()
        setupObservers()
        setupScrollListener()

        viewModel.loadPaymentHistory(refresh = true)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        paymentHistoryAdapter = PaymentHistoryAdapter()

        binding.rvPaymentHistory.layoutManager = LinearLayoutManager(this)
        binding.rvPaymentHistory.adapter = paymentHistoryAdapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadPaymentHistory(refresh = true)
        }
    }

    private fun setupScrollListener() {
        binding.rvPaymentHistory.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                val lastVisiblePosition = layoutManager?.findLastCompletelyVisibleItemPosition() ?: -1
                val totalItemCount = layoutManager?.itemCount ?: 0

                // 마지막 아이템에 도달하면 더 불러오기
                if (lastVisiblePosition >= totalItemCount - 1) {
                    viewModel.loadMore()
                }
            }
        })
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.payments.collect { payments ->
                paymentHistoryAdapter.submitList(payments)
                
                // 빈 상태 표시
                binding.tvEmpty.visibility = if (payments.isEmpty() && !viewModel.isLoading.value) {
                    android.view.View.VISIBLE
                } else {
                    android.view.View.GONE
                }
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
                binding.swipeRefreshLayout.isRefreshing = isLoading
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@PaymentHistoryActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

