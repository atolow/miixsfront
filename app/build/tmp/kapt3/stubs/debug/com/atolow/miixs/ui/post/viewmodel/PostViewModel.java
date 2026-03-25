package com.atolow.miixs.ui.post.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.post.PostResponseDto;
import com.atolow.miixs.data.repository.PostRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/atolow/miixs/ui/post/viewmodel/PostViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_createPostResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "createPostResult", "Lkotlinx/coroutines/flow/StateFlow;", "getCreatePostResult", "()Lkotlinx/coroutines/flow/StateFlow;", "repository", "Lcom/atolow/miixs/data/repository/PostRepository;", "clearCreatePostResult", "", "createPost", "content", "", "app_debug"})
public final class PostViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.PostRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.post.PostResponseDto>> _createPostResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.post.PostResponseDto>> createPostResult = null;
    
    public PostViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.post.PostResponseDto>> getCreatePostResult() {
        return null;
    }
    
    public final void createPost(@org.jetbrains.annotations.NotNull()
    java.lang.String content) {
    }
    
    public final void clearCreatePostResult() {
    }
}