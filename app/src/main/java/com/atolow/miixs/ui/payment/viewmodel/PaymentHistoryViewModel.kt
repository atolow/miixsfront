package com.atolow.miixs.ui.payment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.payment.PaymentResponseDto
import com.atolow.miixs.data.repository.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentHistoryViewModel : ViewModel() {
    private val paymentRepository = PaymentRepository()

    private val _payments = MutableStateFlow<List<PaymentResponseDto>>(emptyList())
    val payments: StateFlow<List<PaymentResponseDto>> = _payments

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _hasMore = MutableStateFlow(true)
    val hasMore: StateFlow<Boolean> = _hasMore

    private var currentPage = 0
    private val pageSize = 20

    fun loadPaymentHistory(refresh: Boolean = false) {
        viewModelScope.launch {
            if (refresh) {
                currentPage = 0
                _payments.value = emptyList()
            }

            _isLoading.value = true
            _error.value = null

            paymentRepository.getPaymentHistory(currentPage, pageSize).fold(
                onSuccess = { pageResponse ->
                    val newPayments = pageResponse.content
                    if (refresh) {
                        _payments.value = newPayments
                    } else {
                        _payments.value = _payments.value + newPayments
                    }

                    _hasMore.value = !pageResponse.last
                    currentPage++
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "결제 내역을 불러올 수 없습니다"
                    _isLoading.value = false
                }
            )
        }
    }

    fun loadMore() {
        if (!_isLoading.value && _hasMore.value) {
            loadPaymentHistory(refresh = false)
        }
    }
}

