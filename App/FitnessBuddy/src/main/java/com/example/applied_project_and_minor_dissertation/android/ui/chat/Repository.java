package com.example.applied_project_and_minor_dissertation.android.ui.chat;

import io.reactivex.Completable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Repository {

    @POST("Hello-convert-and-send")
    Completable sendRestEch(@Query("msg") String message);
}
