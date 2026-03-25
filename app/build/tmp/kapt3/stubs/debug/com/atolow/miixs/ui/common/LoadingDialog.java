package com.atolow.miixs.ui.common;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import com.atolow.miixs.databinding.DialogLoadingBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/atolow/miixs/ui/common/LoadingDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "binding", "Lcom/atolow/miixs/databinding/DialogLoadingBinding;", "setMessage", "", "message", "", "app_debug"})
public final class LoadingDialog extends android.app.Dialog {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.databinding.DialogLoadingBinding binding = null;
    
    public LoadingDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
}