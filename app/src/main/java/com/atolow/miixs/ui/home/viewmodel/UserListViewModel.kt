package com.atolow.miixs.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.atolow.miixs.data.model.user.ProfileResponseDto
import com.atolow.miixs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {
    private val repository = UserRepository()
    
    private val _users = MutableStateFlow<List<ProfileResponseDto>>(emptyList())
    val users: StateFlow<List<ProfileResponseDto>> = _users
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private var selectedLocation: Location? = null
    private var selectedGender: Gender? = null
    private var minAge: Int? = null
    private var maxAge: Int? = null
    
    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getUserList(
                page = 0,
                size = 20,
                location = selectedLocation,
                gender = selectedGender,
                minAge = minAge,
                maxAge = maxAge
            ).onSuccess { pageResponse ->
                // 백엔드에서 이미 최신 가입순으로 정렬되어 있지만, 
                // 추가로 클라이언트 측에서도 정렬하여 최근 가입한 사람이 맨 위로 오도록 보장
                _users.value = pageResponse.content.sortedByDescending { it.createdAt }
                _isLoading.value = false
            }.onFailure { exception ->
                android.util.Log.e("UserListViewModel", "사용자 목록 로드 실패", exception)
                _isLoading.value = false
            }
        }
    }
    
    fun setLocationFilter(location: Location?) {
        selectedLocation = location
        loadUsers()
    }
    
    fun setGenderFilter(gender: Gender?) {
        selectedGender = gender
        loadUsers()
    }
    
    fun setAgeFilter(min: Int?, max: Int?) {
        minAge = min
        maxAge = max
        loadUsers()
    }
}

