# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepattributes Exceptions
# Retrofit 인터페이스의 제네릭 타입 보존 (매우 중요!)
-keepattributes Signature
-keepattributes *Annotation*
# Retrofit 인터페이스와 메서드 보존
-keep interface com.atolow.miixs.data.network.** { *; }
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Retrofit 인터페이스의 모든 메서드 시그니처 보존 (제네릭 타입 포함)
-keepclassmembers interface com.atolow.miixs.data.network.** {
    <methods>;
}
# Retrofit HTTP 메서드 어노테이션 보존 (매우 중요!)
-keep,allowobfuscation,allowshrinking class retrofit2.http.** { *; }
-keepclassmembers,allowobfuscation,allowshrinking class * {
    @retrofit2.http.GET <methods>;
    @retrofit2.http.POST <methods>;
    @retrofit2.http.PUT <methods>;
    @retrofit2.http.DELETE <methods>;
    @retrofit2.http.PATCH <methods>;
    @retrofit2.http.HEAD <methods>;
    @retrofit2.http.OPTIONS <methods>;
}
# Retrofit Response 타입 보존
-keep class retrofit2.Response { *; }
-keep class retrofit2.Response$* { *; }
# ApiClient 보존
-keep class com.atolow.miixs.data.network.ApiClient { *; }
-keep class com.atolow.miixs.data.network.ApiClient$* { *; }
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
# OkHttpClient 및 인터셉터 보존 (매우 중요!)
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep class okhttp3.logging.** { *; }
-keep class okhttp3.Interceptor { *; }
-keep class okhttp3.Interceptor$* { *; }
-keep class com.atolow.miixs.data.network.AuthInterceptor { *; }
-keep class com.atolow.miixs.data.network.AuthInterceptor$* { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Gson 필드 이름 보존 (SerializedName 어노테이션 사용 클래스)
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

# Kotlin Parcelize
-keep interface kotlinx.parcelize.Parcelize
-keep @kotlinx.parcelize.Parcelize class * extends android.os.Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Data Binding
-keep class androidx.databinding.** { *; }
-dontwarn androidx.databinding.**
# ViewBinding 보존 (매우 중요!)
-keep class com.atolow.miixs.databinding.** { *; }
-keep class com.atolow.miixs.databinding.**$* { *; }
# ViewBinding의 모든 메서드와 필드 보존
-keepclassmembers class com.atolow.miixs.databinding.** {
    <methods>;
    <fields>;
}
# ItemProductBinding 특별 보존 (결제 상품 리스트)
-keep class com.atolow.miixs.databinding.ItemProductBinding { *; }
-keep class com.atolow.miixs.databinding.ItemProductBinding$* { *; }
-keepclassmembers class com.atolow.miixs.databinding.ItemProductBinding {
    <methods>;
    <fields>;
}
# 람다 함수 보존 (Kotlin) - 매우 중요!
-keepclassmembers class * {
    *** invoke(...);
}
# Function 인터페이스 보존
-keep interface kotlin.jvm.functions.** { *; }
-keep class kotlin.jvm.functions.** { *; }
# Kotlin 람다 클래스 보존 (클릭 리스너 등)
-keep class **$$Lambda$* { *; }
-keep class **$lambda-* { *; }
-keep class **$*$lambda$* { *; }
# Kotlin SAM 변환 보존
-keepclassmembers class * {
    <methods>;
}
# 모든 람다와 익명 클래스 보존
-keepclassmembers class * {
    <init>(...);
}
# 클릭 리스너 람다 보존
-keepclassmembers class com.atolow.miixs.ui.payment.adapter.ProductAdapter$ProductViewHolder {
    <methods>;
    <fields>;
}

# Navigation
-keepnames class androidx.navigation.fragment.NavHostFragment
-keep class * extends androidx.fragment.app.Fragment {
    public <init>(...);
    <methods>;
}
# Fragment 클래스 보존 (매우 중요!)
-keep class com.atolow.miixs.ui.**.Fragment { *; }
-keep class com.atolow.miixs.ui.**.Fragment$* { *; }
# Activity 클래스 보존 (매우 중요!)
-keep class com.atolow.miixs.ui.**.Activity { *; }
-keep class com.atolow.miixs.ui.**.Activity$* { *; }
# PaymentActivity 메서드 보존 (결제 기능)
-keep class com.atolow.miixs.ui.payment.PaymentActivity { *; }
-keepclassmembers class com.atolow.miixs.ui.payment.PaymentActivity {
    <methods>;
}
# Adapter 클래스 보존 (매우 중요!)
-keep class com.atolow.miixs.ui.**.adapter.** { *; }
-keep class com.atolow.miixs.ui.**.adapter.**$* { *; }
# ProductAdapter 특별 보존 (결제 기능) - 매우 중요!
-keep class com.atolow.miixs.ui.payment.adapter.ProductAdapter { *; }
-keep class com.atolow.miixs.ui.payment.adapter.ProductAdapter$* { *; }
-keepclassmembers class com.atolow.miixs.ui.payment.adapter.ProductAdapter {
    <init>(...);
    <methods>;
    <fields>;
}
-keepclassmembers class com.atolow.miixs.ui.payment.adapter.ProductAdapter$ProductViewHolder {
    <init>(...);
    <methods>;
    <fields>;
}
-keepclassmembers class com.atolow.miixs.ui.payment.adapter.ProductAdapter$ProductDiffCallback {
    <methods>;
}
# ProductClickListener 보존 (클릭 리스너)
-keep class com.atolow.miixs.ui.payment.adapter.ProductAdapter$ProductClickListener { *; }
-keepclassmembers class com.atolow.miixs.ui.payment.adapter.ProductAdapter$ProductClickListener {
    <init>(...);
    <methods>;
    <fields>;
}
# RecyclerView ViewHolder 보존
-keep class * extends androidx.recyclerview.widget.RecyclerView.ViewHolder { *; }
# RecyclerView Adapter 보존
-keep class * extends androidx.recyclerview.widget.RecyclerView.Adapter { *; }
-keep class * extends androidx.recyclerview.widget.ListAdapter { *; }
# View.OnClickListener 보존 (클릭 리스너) - 매우 중요!
-keep interface android.view.View$OnClickListener { *; }
-keep class * implements android.view.View$OnClickListener { *; }
-keepclassmembers class * implements android.view.View$OnClickListener {
    <init>(...);
    <methods>;
    <fields>;
}
# 모든 클릭 리스너 보존
-keepclassmembers class * {
    void onClick(android.view.View);
}
# View 클릭 리스너 설정 메서드 보존
-keepclassmembers class android.view.View {
    void setOnClickListener(android.view.View$OnClickListener);
    android.view.View$OnClickListener getOnClickListener();
}
# ViewBinding의 클릭 리스너 보존
-keepclassmembers class com.atolow.miixs.databinding.ItemProductBinding {
    <methods>;
    <fields>;
}

# Lifecycle
-keep class androidx.lifecycle.** { *; }
-keep class * extends androidx.lifecycle.ViewModel {
    <init>(...);
    <methods>;
}
# ViewModel 클래스 및 메서드 보존
-keep class com.atolow.miixs.ui.**.viewmodel.** { *; }
-keep class com.atolow.miixs.ui.**.viewmodel.**$* { *; }
# Repository 클래스 보존
-keep class com.atolow.miixs.data.repository.** { *; }
-keep class com.atolow.miixs.data.repository.**$* { *; }
# LiveData 및 Flow 보존
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }
-keep class androidx.lifecycle.MediatorLiveData { *; }
-keep class kotlinx.coroutines.flow.** { *; }
-keep interface kotlinx.coroutines.flow.** { *; }

# RxJava
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }
-keep interface io.reactivex.** { *; }

# STOMP WebSocket
-keep class ua.naiksoftware.stomp.** { *; }
-dontwarn ua.naiksoftware.stomp.**

# Google Play Billing - 매우 중요!
-keep class com.android.billingclient.** { *; }
-keep interface com.android.billingclient.** { *; }
-dontwarn com.android.billingclient.**
# ProductDetailsResponseListener 보존
-keep interface com.android.billingclient.api.ProductDetailsResponseListener { *; }
-keep class * implements com.android.billingclient.api.ProductDetailsResponseListener { *; }
-keepclassmembers class * implements com.android.billingclient.api.ProductDetailsResponseListener {
    <init>(...);
    <methods>;
    <fields>;
}
# PurchasesUpdatedListener 보존
-keep interface com.android.billingclient.api.PurchasesUpdatedListener { *; }
-keep class * implements com.android.billingclient.api.PurchasesUpdatedListener { *; }
-keepclassmembers class * implements com.android.billingclient.api.PurchasesUpdatedListener {
    <init>(...);
    <methods>;
    <fields>;
}
# BillingClientStateListener 보존 - 매우 중요!
-keep interface com.android.billingclient.api.BillingClientStateListener { *; }
-keep class * implements com.android.billingclient.api.BillingClientStateListener { *; }
-keepclassmembers class * implements com.android.billingclient.api.BillingClientStateListener {
    <init>(...);
    <methods>;
    <fields>;
}
# BillingClientStateListener의 모든 메서드 명시적 보존
-keepclassmembers class * implements com.android.billingclient.api.BillingClientStateListener {
    void onBillingSetupFinished(com.android.billingclient.api.BillingResult);
    void onBillingServiceDisconnected();
}
# BillingClient 메서드 보존
-keepclassmembers class com.android.billingclient.api.BillingClient {
    <init>(...);
    <methods>;
}
# BillingClient.Builder 보존
-keep class com.android.billingclient.api.BillingClient$Builder { *; }
-keepclassmembers class com.android.billingclient.api.BillingClient$Builder {
    <methods>;
}
# QueryProductDetailsParams 보존
-keep class com.android.billingclient.api.QueryProductDetailsParams { *; }
-keep class com.android.billingclient.api.QueryProductDetailsParams$* { *; }
-keepclassmembers class com.android.billingclient.api.QueryProductDetailsParams {
    <methods>;
}
# ProductDetails 보존
-keep class com.android.billingclient.api.ProductDetails { *; }
-keep class com.android.billingclient.api.ProductDetails$* { *; }
# BillingResult 보존
-keep class com.android.billingclient.api.BillingResult { *; }
-keep class com.android.billingclient.api.BillingResult$* { *; }
# PaymentActivity의 모든 익명 클래스 및 내부 클래스 보존
-keep class com.atolow.miixs.ui.payment.PaymentActivity { *; }
-keep class com.atolow.miixs.ui.payment.PaymentActivity$* { *; }
-keepclassmembers class com.atolow.miixs.ui.payment.PaymentActivity {
    <init>(...);
    <methods>;
    <fields>;
}
-keepclassmembers class com.atolow.miixs.ui.payment.PaymentActivity$* {
    <init>(...);
    <methods>;
    <fields>;
}
# ProductDetailsResponseListenerImpl 보존
-keep class com.atolow.miixs.ui.payment.PaymentActivity$ProductDetailsResponseListenerImpl { *; }
-keepclassmembers class com.atolow.miixs.ui.payment.PaymentActivity$ProductDetailsResponseListenerImpl {
    <init>(...);
    <methods>;
    <fields>;
}
# BillingClientStateListenerImpl 보존 (별도 파일로 분리됨) - 매우 중요!
# 완전히 보존 (제거 금지, 이름 변경 금지, 최적화 금지)
-keep class com.atolow.miixs.ui.payment.BillingClientStateListenerImpl {
    *;
}
-keepclassmembers class com.atolow.miixs.ui.payment.BillingClientStateListenerImpl {
    *;
}
# BillingClientStateListenerImpl의 모든 메서드 명시적 보존
-keepclassmembers class com.atolow.miixs.ui.payment.BillingClientStateListenerImpl {
    void onBillingSetupFinished(com.android.billingclient.api.BillingResult);
    void onBillingServiceDisconnected();
    <init>(com.atolow.miixs.ui.payment.PaymentActivity);
    <init>();
}
# BillingClientStateListenerImpl의 모든 필드 보존
-keepclassmembers class com.atolow.miixs.ui.payment.BillingClientStateListenerImpl {
    <fields>;
}
# BillingClientStateListenerImpl을 최적화하지 않음
-keep,allowoptimization,allowobfuscation class com.atolow.miixs.ui.payment.BillingClientStateListenerImpl {
    *;
}

# ProductDetailsResponseListenerImpl 보존 (별도 파일로 분리됨) - 매우 중요!
# 완전히 보존 (제거 금지, 이름 변경 금지, 최적화 금지)
-keep class com.atolow.miixs.ui.payment.ProductDetailsResponseListenerImpl {
    *;
}
-keepclassmembers class com.atolow.miixs.ui.payment.ProductDetailsResponseListenerImpl {
    *;
}
# ProductDetailsResponseListenerImpl의 모든 메서드 명시적 보존
-keepclassmembers class com.atolow.miixs.ui.payment.ProductDetailsResponseListenerImpl {
    void onProductDetailsResponse(com.android.billingclient.api.BillingResult, java.util.List);
    <init>(com.atolow.miixs.ui.payment.PaymentActivity, com.atolow.miixs.data.model.payment.ProductResponseDto);
    <init>();
}
# ProductDetailsResponseListenerImpl의 모든 필드 보존
-keepclassmembers class com.atolow.miixs.ui.payment.ProductDetailsResponseListenerImpl {
    <fields>;
}
# ProductDetailsResponseListenerImpl을 최적화하지 않음
-keep,allowoptimization,allowobfuscation class com.atolow.miixs.ui.payment.ProductDetailsResponseListenerImpl {
    *;
}

# Model classes (데이터 클래스 보존)
-keep class com.atolow.miixs.data.model.** { *; }
-keep class com.atolow.miixs.domain.** { *; }
# 네트워크 관련 클래스 보존
-keep class com.atolow.miixs.data.network.** { *; }
# Gson 직렬화를 위한 필드 보존
-keepclassmembers class com.atolow.miixs.data.model.** {
    <fields>;
}
# ApiResponse 제네릭 타입 보존
-keep class com.atolow.miixs.data.model.ApiResponse { *; }
-keep class com.atolow.miixs.data.model.ApiResponse$* { *; }
-keep class com.atolow.miixs.data.model.PageResponse { *; }
-keep class com.atolow.miixs.data.model.PageResponse$* { *; }

# Application 클래스 보존
-keep class com.atolow.miixs.MiixsApplication { *; }
-keep class com.atolow.miixs.MiixsApplication$* { *; }

# TokenManager 보존
-keep class com.atolow.miixs.data.local.TokenManager { *; }
-keep class com.atolow.miixs.data.local.TokenManager$* { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Kotlin Coroutines 보존 (suspend 함수 등)
-keep class kotlinx.coroutines.** { *; }
-keep interface kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**
# Continuation 보존
-keep class kotlin.coroutines.Continuation { *; }
-keep class kotlin.coroutines.Continuation$* { *; }
# suspend 함수는 ViewModel과 Repository 클래스를 보존하면 자동으로 보존됨
