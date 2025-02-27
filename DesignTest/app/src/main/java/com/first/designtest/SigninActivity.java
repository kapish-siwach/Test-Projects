package com.first.designtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SigninActivity extends AppCompatActivity {

    TextView signUpBtn,forgetPassword;
    LinearLayout backBtn;
    Button signInBtn;
    ImageView googleLogin, appleLogin, facebookLogin, twitterLogin;
    TextInputEditText emailInput, passwordInput;
    TextInputLayout emailLayout, passwordLayout;
    AppCompatCheckBox rememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signUpBtn = findViewById(R.id.signupBtnL);
        backBtn = findViewById(R.id.backBtn);
        signInBtn = findViewById(R.id.signInBtn);

        googleLogin = findViewById(R.id.googleLogin);
        appleLogin = findViewById(R.id.appleLogin);
        facebookLogin = findViewById(R.id.facebookLogin);
        twitterLogin = findViewById(R.id.twitterLogin);

        emailLayout = findViewById(R.id.emailLayout);
        emailInput = findViewById(R.id.emailInput);
        passwordLayout = findViewById(R.id.passwordLayout);
        passwordInput = findViewById(R.id.passwordInput);

        rememberMe = findViewById(R.id.rememberMe);
        forgetPassword = findViewById(R.id.forgetPassword);

        signUpBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, SignupActivity.class));
        });

        backBtn.setOnClickListener(view -> {
            finish();
        });

        signInBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

    }
}