package com.atolow.miixs.ui.home.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.Gender;
import com.atolow.miixs.data.model.Location;
import com.atolow.miixs.data.model.post.PostResponseDto;
import com.atolow.miixs.data.repository.PostRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\u001f\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u0015J\u0010\u0010 \u001a\u00020\u00192\b\u0010!\u001a\u0004\u0018\u00010\u0017R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/atolow/miixs/ui/home/viewmodel/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_posts", "", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "isLoading", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "maxAge", "", "Ljava/lang/Integer;", "minAge", "posts", "getPosts", "repository", "Lcom/atolow/miixs/data/repository/PostRepository;", "selectedGender", "Lcom/atolow/miixs/data/model/Gender;", "selectedLocation", "Lcom/atolow/miixs/data/model/Location;", "loadPosts", "", "setAgeFilter", "min", "max", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "setGenderFilter", "gender", "setLocationFilter", "location", "app_release"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.PostRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.atolow.miixs.data.model.post.PostResponseDto>> _posts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.post.PostResponseDto>> posts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.data.model.Location selectedLocation;
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.data.model.Gender selectedGender;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer minAge;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer maxAge;
    
    public HomeViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.post.PostResponseDto>> getPosts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void loadPosts() {
    }
    
    public final void setLocationFilter(@org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Location location) {
    }
    
    public final void setGenderFilter(@org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Gender gender) {
    }
    
    public final void setAgeFilter(@org.jetbrains.annotations.Nullable()
    java.lang.Integer min, @org.jetbrains.annotations.Nullable()
    java.lang.Integer max) {
    }
}