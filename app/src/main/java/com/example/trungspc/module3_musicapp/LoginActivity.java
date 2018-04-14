package com.example.trungspc.module3_musicapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login Activity";
    EditText etUsername;
    EditText etPassword;
    Button btLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        setupUI();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Tạo ra cho vui thôi =))", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        btLogin.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating");
        progressDialog.show();

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        final String[] messageBody = {""};

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://qkserver1.herokuapp.com/api/")
                .build();

        final ILoginService loginService = retrofit.create(ILoginService.class);
        loginService.login(new Login().new LoginRequest(
                "trung",
                "secret"
        )).enqueue(new Callback<Login.LoginResponse>() {
            @Override
            public void onResponse(Call<Login.LoginResponse> call, Response<Login.LoginResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                messageBody[0] = response.body().toString();
            }

            @Override
            public void onFailure(Call<Login.LoginResponse> call, Throwable t) {

            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess(messageBody[0]);
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void onLoginSuccess(String message) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle("Login Success");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
        btLogin.setEnabled(true);
    }

    private void setupUI() {
        etUsername = findViewById(R.id.input_user);
        etPassword = findViewById(R.id.input_password);
        btLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.link_signup);
    }

    private boolean validate() {
        boolean valid = true;

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty()) {
            etUsername.setError("Enter a valid username");
            valid = false;
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()) {
            etUsername.setError("Enter a valid password");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        return valid;
    }

}
