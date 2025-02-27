package com.first.smartstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends BottomSheetDialogFragment {

    TextInputEditText emailInput, passwordInput;
    TextInputLayout emailLayout, passwordLayout;
    Button loginBtn;
    private UserDB userDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initValues(view);

        loginBtn.setOnClickListener(v->{
            checkValidation();
        });

    }

    private void checkValidation() {
        SharedPreferences sp=getActivity().getSharedPreferences("login",0);
        SharedPreferences.Editor editor=sp.edit();
        if (emailInput.getText().toString().equalsIgnoreCase("") || passwordInput.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getActivity(), "All fields are required!", Toast.LENGTH_SHORT).show();
        } else {
         UserDetails user=  userDB.getUserByEmailPassword(emailInput.getText().toString(), passwordInput.getText().toString());
            if (user!=null){
                Toast.makeText(getActivity(), "Login Successful..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),HomeActivity.class));
                clearFields();
                editor.putBoolean("isLoggedIn",true);
                editor.putString("userEmail", user.getEmail());
                editor.putString("password", user.getPassword());
                editor.apply();
                dismiss();
            }
            else {
                Toast.makeText(getActivity(), "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clearFields() {
        emailInput.setText("");
        passwordInput.setText("");
    }


    private void initValues(View view) {
        userDB = new UserDB(LoginFragment.this.getContext());
        loginBtn = view.findViewById(R.id.signInBtn);
        emailLayout = view.findViewById(R.id.emailLayout);
        passwordInput = view.findViewById(R.id.passwordInput);
        passwordLayout = view.findViewById(R.id.passwordLayout);
        emailInput = view.findViewById(R.id.emailInput);

    }
}