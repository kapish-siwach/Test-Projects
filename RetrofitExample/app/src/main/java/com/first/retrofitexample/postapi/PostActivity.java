package com.first.retrofitexample.postapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.first.retrofitexample.HomeActivity;
import com.first.retrofitexample.R;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PostActivity extends AppCompatActivity {

    ImageView pristineImg;
    TextInputEditText emailInput, passwordInput;
    TextInputLayout emailLayout, passwordLayout;
    ProgressBar progressIndicator;
    Button signInBtn;
    TextView errorText;
    MaterialCheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        if (sp.getBoolean("isLoggedIn",false))
            startActivity(new Intent(PostActivity.this, HomeActivity.class));

        signInBtn.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty()){
            emailLayout.setError("Please Enter Email");
            return;
        } else {
            emailLayout.setError(null);
        }
        if (password.isEmpty()){
            passwordLayout.setError("Please Enter Password");
            return;
        } else {
            passwordLayout.setError(null);
        }

        progressIndicator.setVisibility(View.VISIBLE);


        LoginRequest loginRequest = new LoginRequest(email, password);


        RetrofitInstance.getApiInterface().loginUser(loginRequest).enqueue(new Callback<List<ResponseData>>() {
            @Override
            public void onResponse(Call<List<ResponseData>> call, retrofit2.Response<List<ResponseData>> response) {
                progressIndicator.setVisibility(View.GONE);
                errorText.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    ResponseData user = response.body().get(0);
                    if (user.getEmail() != null) {
                        Toast.makeText(PostActivity.this, "Login Successful! " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        checkSession();
                        Intent intent = new Intent(PostActivity.this, HomeActivity.class);
                        passData(user);
                        startActivity(intent);
                        clearFields();
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        errorText.setText(R.string.invalid_credentials);
                    }
                }
                 else {
                    Toast.makeText(PostActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseData>> call, Throwable t) {
                progressIndicator.setVisibility(View.GONE);
                Toast.makeText(PostActivity.this, "API Call Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API Failure", t.getMessage());
            }
        });

    }

    private void passData(ResponseData user) {
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putString("companyId", user.getCompanyId());
        editor.putString("roleName", user.getRoleName());
        editor.apply();
    }

    private void checkSession() {
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        if (rememberMe.isChecked()) {
            editor.putBoolean("isLoggedIn", true);
            Log.i("checkSession", ""+rememberMe.isChecked());
            editor.apply();
        }
    }

    private void clearFields() {
        emailInput.setText("");
        passwordInput.setText("");
        rememberMe.setChecked(false);
        errorText.setVisibility(View.GONE);
        finish();
    }

    private void initViews() {
        rememberMe = findViewById(R.id.rememberMe);
        rememberMe.setChecked(true);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        errorText = findViewById(R.id.errorText);
        pristineImg = findViewById(R.id.pristineImg);
        signInBtn = findViewById(R.id.signInBtn);
        progressIndicator = findViewById(R.id.progrssBarLogin);
        Glide.with(this)
                .load("https://dev4.pristinefulfil.com/assets/images/vendor_panel_img/mylogo.png")
                .into(pristineImg);
    }
}
