package com.atolow.miixs.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J+\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\t2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bJ\u001a\u0010\u001c\u001a\u00020\t2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bJ\b\u0010\u001d\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/atolow/miixs/util/ImagePicker;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "cameraImageUri", "Landroid/net/Uri;", "onImageSelected", "Lkotlin/Function1;", "", "checkPermissions", "", "createImageFile", "Ljava/io/File;", "handleActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "handleRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)Z", "pickImageFromCamera", "pickImageFromGallery", "requestPermissions", "Companion", "app_debug"})
public final class ImagePicker {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Activity activity = null;
    public static final int REQUEST_CODE_GALLERY = 1001;
    public static final int REQUEST_CODE_CAMERA = 1002;
    public static final int REQUEST_CODE_PERMISSIONS = 1003;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PERMISSION_CAMERA = "android.permission.CAMERA";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PERMISSION_READ_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PERMISSION_READ_MEDIA_IMAGES = "android.permission.READ_MEDIA_IMAGES";
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri cameraImageUri;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onImageSelected;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.util.ImagePicker.Companion Companion = null;
    
    public ImagePicker(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        super();
    }
    
    public final void pickImageFromGallery(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onImageSelected) {
    }
    
    public final void pickImageFromCamera(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onImageSelected) {
    }
    
    public final boolean handleActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
        return false;
    }
    
    public final boolean handleRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
        return false;
    }
    
    private final boolean checkPermissions() {
        return false;
    }
    
    private final void requestPermissions() {
    }
    
    private final java.io.File createImageFile() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/atolow/miixs/util/ImagePicker$Companion;", "", "()V", "PERMISSION_CAMERA", "", "PERMISSION_READ_MEDIA_IMAGES", "PERMISSION_READ_STORAGE", "REQUEST_CODE_CAMERA", "", "REQUEST_CODE_GALLERY", "REQUEST_CODE_PERMISSIONS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}