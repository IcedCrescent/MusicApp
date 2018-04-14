package com.example.trungspc.module3_musicapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    @POST("login")
    Call<Login.LoginResponse> login(@Body Login.LoginRequest loginRequest);
}
