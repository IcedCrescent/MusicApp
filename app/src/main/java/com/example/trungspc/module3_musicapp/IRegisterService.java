package com.example.trungspc.module3_musicapp;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRegisterService {
    @POST("register")
    Call<Register.RegisterResponse> register(@Body Register.RegisterRequest registerRequest);
}
