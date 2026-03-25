package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.report.CreateReportRequestDto;
import com.atolow.miixs.data.model.report.CreateUserReportRequestDto;
import com.atolow.miixs.data.model.report.ReportResponseDto;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/atolow/miixs/data/network/ReportApi;", "", "createReport", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "Lcom/atolow/miixs/data/model/report/ReportResponseDto;", "request", "Lcom/atolow/miixs/data/model/report/CreateReportRequestDto;", "(Lcom/atolow/miixs/data/model/report/CreateReportRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createUserReport", "Lcom/atolow/miixs/data/model/report/CreateUserReportRequestDto;", "(Lcom/atolow/miixs/data/model/report/CreateUserReportRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReportCount", "", "userId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ReportApi {
    
    @retrofit2.http.POST(value = "api/reports")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createReport(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.report.CreateReportRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.report.ReportResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/reports/users")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createUserReport(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.report.CreateUserReportRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.report.ReportResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/reports/users/{userId}/count")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReportCount(@retrofit2.http.Path(value = "userId")
    long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Long>>> $completion);
}