package com.atolow.miixs.data.model.post;

import com.atolow.miixs.data.model.Gender;
import com.atolow.miixs.data.model.Location;
import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b(\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0014J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0011H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u00102\u001a\u00020\u0006H\u00c6\u0003J\u0094\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\u00112\b\u00106\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00107\u001a\u00020\u000eH\u00d6\u0001J\t\u00108\u001a\u00020\u0006H\u00d6\u0001R\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0016\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010$R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001b\u00a8\u00069"}, d2 = {"Lcom/atolow/miixs/data/model/post/PostResponseDto;", "", "postId", "", "authorId", "authorName", "", "authorUsername", "authorProfileImageUrl", "authorLocation", "Lcom/atolow/miixs/data/model/Location;", "authorGender", "Lcom/atolow/miixs/data/model/Gender;", "authorAge", "", "content", "isActive", "", "createdAt", "modifiedAt", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/atolow/miixs/data/model/Location;Lcom/atolow/miixs/data/model/Gender;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getAuthorAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAuthorGender", "()Lcom/atolow/miixs/data/model/Gender;", "getAuthorId", "()J", "getAuthorLocation", "()Lcom/atolow/miixs/data/model/Location;", "getAuthorName", "()Ljava/lang/String;", "getAuthorProfileImageUrl", "getAuthorUsername", "getContent", "getCreatedAt", "()Z", "getModifiedAt", "getPostId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/atolow/miixs/data/model/Location;Lcom/atolow/miixs/data/model/Gender;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)Lcom/atolow/miixs/data/model/post/PostResponseDto;", "equals", "other", "hashCode", "toString", "app_release"})
public final class PostResponseDto {
    @com.google.gson.annotations.SerializedName(value = "postId")
    private final long postId = 0L;
    @com.google.gson.annotations.SerializedName(value = "authorId")
    private final long authorId = 0L;
    @com.google.gson.annotations.SerializedName(value = "authorName")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String authorName = null;
    @com.google.gson.annotations.SerializedName(value = "authorUsername")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String authorUsername = null;
    @com.google.gson.annotations.SerializedName(value = "authorProfileImageUrl")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String authorProfileImageUrl = null;
    @com.google.gson.annotations.SerializedName(value = "authorLocation")
    @org.jetbrains.annotations.Nullable()
    private final com.atolow.miixs.data.model.Location authorLocation = null;
    @com.google.gson.annotations.SerializedName(value = "authorGender")
    @org.jetbrains.annotations.Nullable()
    private final com.atolow.miixs.data.model.Gender authorGender = null;
    @com.google.gson.annotations.SerializedName(value = "authorAge")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer authorAge = null;
    @com.google.gson.annotations.SerializedName(value = "content")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String content = null;
    @com.google.gson.annotations.SerializedName(value = "isActive")
    private final boolean isActive = false;
    @com.google.gson.annotations.SerializedName(value = "createdAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String createdAt = null;
    @com.google.gson.annotations.SerializedName(value = "modifiedAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String modifiedAt = null;
    
    public final long component1() {
        return 0L;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Location component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Gender component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.model.post.PostResponseDto copy(long postId, long authorId, @org.jetbrains.annotations.NotNull()
    java.lang.String authorName, @org.jetbrains.annotations.Nullable()
    java.lang.String authorUsername, @org.jetbrains.annotations.Nullable()
    java.lang.String authorProfileImageUrl, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Location authorLocation, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Gender authorGender, @org.jetbrains.annotations.Nullable()
    java.lang.Integer authorAge, @org.jetbrains.annotations.NotNull()
    java.lang.String content, boolean isActive, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String modifiedAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    public PostResponseDto(long postId, long authorId, @org.jetbrains.annotations.NotNull()
    java.lang.String authorName, @org.jetbrains.annotations.Nullable()
    java.lang.String authorUsername, @org.jetbrains.annotations.Nullable()
    java.lang.String authorProfileImageUrl, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Location authorLocation, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Gender authorGender, @org.jetbrains.annotations.Nullable()
    java.lang.Integer authorAge, @org.jetbrains.annotations.NotNull()
    java.lang.String content, boolean isActive, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String modifiedAt) {
        super();
    }
    
    public final long getPostId() {
        return 0L;
    }
    
    public final long getAuthorId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthorName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAuthorUsername() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAuthorProfileImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Location getAuthorLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Gender getAuthorGender() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getAuthorAge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContent() {
        return null;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getModifiedAt() {
        return null;
    }
}