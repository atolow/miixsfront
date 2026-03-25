package com.atolow.miixs.ui.payment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.payment.ProductResponseDto
import com.atolow.miixs.data.repository.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    private val paymentRepository = PaymentRepository()

    private val _products = MutableStateFlow<List<ProductResponseDto>>(emptyList())
    val products: StateFlow<List<ProductResponseDto>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            paymentRepository.getActiveProducts().fold(
                onSuccess = { productList ->
                    _products.value = productList
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "상품 목록을 불러올 수 없습니다"
                    _isLoading.value = false
                }
            )
        }
    }
}

