package com.atolow.miixs.data.local;

import android.content.Context;
import android.content.SharedPreferences;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/atolow/miixs/data/local/NotificationSettings;", "", "()V", "KEY_PUSH_NOTIFICATION_ENABLED", "", "PREFS_NAME", "prefs", "Landroid/content/SharedPreferences;", "init", "", "context", "Landroid/content/Context;", "isPushNotificationEnabled", "", "setPushNotificationEnabled", "enabled", "app_debug"})
public final class NotificationSettings {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "miixs_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PUSH_NOTIFICATION_ENABLED = "push_notification_enabled";
    private static android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.data.local.NotificationSettings INSTANCE = null;
    
    private NotificationSettings() {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final boolean isPushNotificationEnabled() {
        return false;
    }
    
    public final void setPushNotificationEnabled(boolean enabled) {
    }
}