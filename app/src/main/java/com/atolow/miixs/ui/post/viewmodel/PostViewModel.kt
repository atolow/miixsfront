package com.atolow.miixs.ui.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.post.PostResponseDto
import com.atolow.miixs.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    
    private val _createPostResult = MutableStateFlow<Result<PostResponseDto>?>(null)
    val createPostResult: StateFlow<Result<PostResponseDto>?> = _createPostResult
    
    fun createPost(content: String) {
        viewModelScope.launch {
            _createPostResult.value = repository.createPost(content)
        }
    }
    
    fun clearCreatePostResult() {
        _createPostResult.value = null
    }
}

