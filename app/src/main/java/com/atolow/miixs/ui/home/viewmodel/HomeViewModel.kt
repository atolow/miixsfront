package com.atolow.miixs.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.Gender
import com.atolow.miixs.data.model.Location
import com.atolow.miixs.data.model.post.PostResponseDto
import com.atolow.miixs.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = PostRepository()
    
    private val _posts = MutableStateFlow<List<PostResponseDto>>(emptyList())
    val posts: StateFlow<List<PostResponseDto>> = _posts
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private var selectedLocation: Location? = null
    private var selectedGender: Gender? = null
    private var minAge: Int? = null
    private var maxAge: Int? = null
    
    fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getPostListWithFilters(selectedLocation, selectedGender, minAge, maxAge).onSuccess { pageResponse ->
                _posts.value = pageResponse.content
                _isLoading.value = false
            }.onFailure {
                _isLoading.value = false
            }
        }
    }
    
    fun setLocationFilter(location: Location?) {
        selectedLocation = location
        loadPosts()
    }
    
    fun setGenderFilter(gender: Gender?) {
        selectedGender = gender
        loadPosts()
    }
    
    fun setAgeFilter(min: Int?, max: Int?) {
        minAge = min
        maxAge = max
        loadPosts()
    }
}

