package com.atolow.miixs.data.repository;

import android.util.Log;
import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.report.CreateReportRequestDto;
import com.atolow.miixs.data.model.report.CreateUserReportRequestDto;
import com.atolow.miixs.data.model.report.ReportResponseDto;
import com.atolow.miixs.data.network.ApiClient;
import com.google.gson.Gson;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J0\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0014"}, d2 = {"Lcom/atolow/miixs/data/repository/ReportRepository;", "", "()V", "TAG", "", "gson", "Lcom/google/gson/Gson;", "reportApi", "Lcom/atolow/miixs/data/network/ReportApi;", "reportPost", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/report/ReportResponseDto;", "postId", "", "reason", "reportPost-0E7RQCE", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reportUser", "userId", "reportUser-0E7RQCE", "app_release"})
public final class ReportRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.network.ReportApi reportApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "ReportRepository";
    
    public ReportRepository() {
        super();
    }
}