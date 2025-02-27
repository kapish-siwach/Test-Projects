package com.first.noname;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginDialog extends BottomSheetDialogFragment {

    private TextInputEditText emailInput, passwordInput;
    private TextInputLayout emailLayout, passwordLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        emailInput = view.findViewById(R.id.emailInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        emailLayout = view.findViewById(R.id.emailLayout);
        passwordLayout = view.findViewById(R.id.passwordLayout);
        Button signInBtn = view.findViewById(R.id.signInBtn);
        TextView signUpBtn = view.findViewById(R.id.signUpBtnLogin);


        signInBtn.setOnClickListener(v ->{
                validateAndLogin();
                });

        signUpBtn.setOnClickListener(v -> {
            dismiss();
            SignupDialog signupDialog = new SignupDialog();
            signupDialog.show(requireActivity().getSupportFragmentManager(), "SignupDialog");
        });

        return view;
    }

    private void validateAndLogin() {
        String email = Objects.requireNonNull(emailInput.getText()).toString().trim();
        String password = Objects.requireNonNull(passwordInput.getText()).toString().trim();

        SharedPreferences sp = requireContext().getSharedPreferences("LoginStatus", MODE_PRIVATE);
        String userEmail = sp.getString("email", null);
        String userPassword = sp.getString("password", null);


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Invalid Email format");
            return;
        } else if (!Objects.equals(userEmail, email)) {
            emailLayout.setError("Email not found");
            return;
        } else {
            emailLayout.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError("Password is required");
            return;
        } else if (password.length() < 6) {
            passwordLayout.setError("Password must be at least 6 characters");
            return;
        } else if (!Objects.equals(userPassword, password)) {
            passwordLayout.setError("Incorrect Password");
            return;
        } else {
            passwordLayout.setError(null);
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();

        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(requireActivity(), MainActivity.class);
        startActivity(intent);

        dismiss();
    }
}
