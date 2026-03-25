package com.atolow.miixs.data.model.user;

import com.atolow.miixs.data.model.Gender;
import com.atolow.miixs.data.model.Location;
import com.atolow.miixs.data.model.PageResponse;
import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b(\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010#J\u0010\u0010,\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001cJ\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u000b\u00104\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010#J\u0098\u0001\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013H\u00c6\u0001\u00a2\u0006\u0002\u00107J\u0013\u00108\u001a\u00020\u00112\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\rH\u00d6\u0001J\t\u0010;\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010$\u001a\u0004\b\'\u0010#R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001a\u00a8\u0006<"}, d2 = {"Lcom/atolow/miixs/data/model/user/DashboardResponseDto;", "", "id", "", "username", "", "name", "profileImageUrl", "location", "Lcom/atolow/miixs/data/model/Location;", "gender", "Lcom/atolow/miixs/data/model/Gender;", "age", "", "bio", "miixsCash", "blockNewChats", "", "blockedUsers", "Lcom/atolow/miixs/data/model/PageResponse;", "Lcom/atolow/miixs/data/model/user/BlockedUserDto;", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/atolow/miixs/data/model/Location;Lcom/atolow/miixs/data/model/Gender;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Lcom/atolow/miixs/data/model/PageResponse;)V", "getAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBio", "()Ljava/lang/String;", "getBlockNewChats", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getBlockedUsers", "()Lcom/atolow/miixs/data/model/PageResponse;", "getGender", "()Lcom/atolow/miixs/data/model/Gender;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLocation", "()Lcom/atolow/miixs/data/model/Location;", "getMiixsCash", "getName", "getProfileImageUrl", "getUsername", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/atolow/miixs/data/model/Location;Lcom/atolow/miixs/data/model/Gender;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Lcom/atolow/miixs/data/model/PageResponse;)Lcom/atolow/miixs/data/model/user/DashboardResponseDto;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class DashboardResponseDto {
    @com.google.gson.annotations.SerializedName(value = "id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @com.google.gson.annotations.SerializedName(value = "username")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String username = null;
    @com.google.gson.annotations.SerializedName(value = "name")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String name = null;
    @com.google.gson.annotations.SerializedName(value = "profileImageUrl")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String profileImageUrl = null;
    @com.google.gson.annotations.SerializedName(value = "location")
    @org.jetbrains.annotations.Nullable()
    private final com.atolow.miixs.data.model.Location location = null;
    @com.google.gson.annotations.SerializedName(value = "gender")
    @org.jetbrains.annotations.Nullable()
    private final com.atolow.miixs.data.model.Gender gender = null;
    @com.google.gson.annotations.SerializedName(value = "age")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer age = null;
    @com.google.gson.annotations.SerializedName(value = "bio")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String bio = null;
    @com.google.gson.annotations.SerializedName(value = "miixsCash")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long miixsCash = null;
    @com.google.gson.annotations.SerializedName(value = "blockNewChats")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean blockNewChats = null;
    @com.google.gson.annotations.SerializedName(value = "blockedUsers")
    @org.jetbrains.annotations.Nullable()
    private final com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.user.BlockedUserDto> blockedUsers = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.user.BlockedUserDto> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Location component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Gender component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.model.user.DashboardResponseDto copy(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.Nullable()
    java.lang.String username, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String profileImageUrl, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Location location, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Gender gender, @org.jetbrains.annotations.Nullable()
    java.lang.Integer age, @org.jetbrains.annotations.Nullable()
    java.lang.String bio, @org.jetbrains.annotations.Nullable()
    java.lang.Long miixsCash, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean blockNewChats, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.user.BlockedUserDto> blockedUsers) {
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
    
    public DashboardResponseDto(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.Nullable()
    java.lang.String username, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String profileImageUrl, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Location location, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.Gender gender, @org.jetbrains.annotations.Nullable()
    java.lang.Integer age, @org.jetbrains.annotations.Nullable()
    java.lang.String bio, @org.jetbrains.annotations.Nullable()
    java.lang.Long miixsCash, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean blockNewChats, @org.jetbrains.annotations.Nullable()
    com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.user.BlockedUserDto> blockedUsers) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUsername() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getProfileImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Location getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.Gender getGender() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getAge() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBio() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getMiixsCash() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getBlockNewChats() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.user.BlockedUserDto> getBlockedUsers() {
        return null;
    }
}