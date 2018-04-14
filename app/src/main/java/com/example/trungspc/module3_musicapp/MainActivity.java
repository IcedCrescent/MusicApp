package com.example.trungspc.module3_musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button btTestLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btTestLogin = findViewById(R.id.btnTestLogin);
        btTestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        //create a Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://qkserver1.herokuapp.com/api/")
                .build();

        //create request and response object

        //create an interface defining request

        // call
        IRegisterService registerService = retrofit.create(IRegisterService.class);
        registerService.register(new Register().new RegisterRequest(
                "trung",
                "secret",
                "stu"
        )).enqueue(new Callback<Register.RegisterResponse>() {
            @Override
            public void onResponse(Call<Register.RegisterResponse> call, Response<Register.RegisterResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<Register.RegisterResponse> call, Throwable t) {

            }
        });

        final ILoginService loginService = retrofit.create(ILoginService.class);
        loginService.login(new Login().new LoginRequest(
                "trung",
                "secret"
        )).enqueue(new Callback<Login.LoginResponse>() {
            @Override
            public void onResponse(Call<Login.LoginResponse> call, Response<Login.LoginResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Login.LoginResponse> call, Throwable t) {

            }
        });
    }
}
