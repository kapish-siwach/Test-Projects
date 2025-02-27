package com.first.noname;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignupDialog extends BottomSheetDialogFragment {

     TextInputLayout firstNameLayout, lastNameLayout, emailLayout, phoneLayout, genderLayout, passwordLayout, confirmPasswordLayout;
     TextInputEditText firstNameInput, lastNameInput, emailInput, phoneInput, passwordInput, confirmPasswordInput;
     MaterialAutoCompleteTextView genderInput;
     TextView loginBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_dialog, container, false);

        loginBtn = view.findViewById(R.id.signInBtnFromSignup);
        firstNameLayout = view.findViewById(R.id.firstNameLayout);
        lastNameLayout = view.findViewById(R.id.lastNameLayout);
        emailLayout = view.findViewById(R.id.emailLayout);
        phoneLayout = view.findViewById(R.id.phoneLayout);
        genderLayout = view.findViewById(R.id.genderLayout);
        passwordLayout = view.findViewById(R.id.passwordLayout);
        confirmPasswordLayout = view.findViewById(R.id.confirmPasswordLayout);

        firstNameInput = view.findViewById(R.id.firstNameInput);
        lastNameInput = view.findViewById(R.id.lastNameInput);
        emailInput = view.findViewById(R.id.emailInput);
        phoneInput = view.findViewById(R.id.phoneInput);
        genderInput = view.findViewById(R.id.genderInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        confirmPasswordInput = view.findViewById(R.id.confirmPasswordInput);

        String[] genderOptions = {"Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, genderOptions);
        genderInput.setAdapter(adapter);

        genderInput.setOnClickListener(v -> genderInput.showDropDown());


        loginBtn.setOnClickListener(v -> {
            dismiss();
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.show(requireActivity().getSupportFragmentManager(), "LoginDialog");
        });


        view.findViewById(R.id.signUpBtn).setOnClickListener(v -> validateAndSubmit());

        return view;
    }

    private void validateAndSubmit() {
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String gender = genderInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();


        if (firstName.isEmpty()) {
            firstNameLayout.setError("First name is required");
            return;
        } else {
            firstNameLayout.setError(null);
        }

        if (lastName.isEmpty()) {
            lastNameLayout.setError("Last name is required");
            return;
        } else {
            lastNameLayout.setError(null);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Invalid email address");
            return;
        } else {
            emailLayout.setError(null);
        }

        if (!phone.matches("^[0-9]{10}$")) {
            phoneLayout.setError("Enter a valid 10-digit phone number");
            return;
        } else {
            phoneLayout.setError(null);
        }

        if (gender.isEmpty()) {
            genderLayout.setError("Please select your gender");
            return;
        } else {
            genderLayout.setError(null);
        }

        if (password.length() < 6) {
            passwordLayout.setError("Password must be at least 6 characters");
            return;
        } else {
            passwordLayout.setError(null);
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordLayout.setError("Passwords do not match");
            return;
        } else {
            confirmPasswordLayout.setError(null);
        }

        Intent intent = new Intent(getActivity(), MainActivity.class);

        SharedPreferences sp= getContext().getSharedPreferences("LoginStatus",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putString("firstName",firstName);
        editor.putString("lastName",lastName);
        editor.putString("phone",phone);
        editor.putString("gender",gender);
        editor.apply();
        startActivity(intent);
        Toast.makeText(getActivity(), "Sign up Successful", Toast.LENGTH_SHORT).show();
    }
}
