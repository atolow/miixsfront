package com.atolow.miixs.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()
    
    private val _loginResult = MutableStateFlow<Result<com.atolow.miixs.data.model.auth.LoginResponseDto>?>(null)
    val loginResult: StateFlow<Result<com.atolow.miixs.data.model.auth.LoginResponseDto>?> = _loginResult
    
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = repository.login(username, password)
        }
    }
}

