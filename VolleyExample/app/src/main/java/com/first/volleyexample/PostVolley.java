package com.first.volleyexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostVolley extends AppCompatActivity {
    TextInputEditText emailInput, passwordInput;
    Button signInBtn;
    ProgressBar progressBarLogin;
    TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_volley);
        initViews();

        signInBtn.setOnClickListener(v -> {
            if (emailInput.getText().toString().trim().isEmpty() || passwordInput.getText().toString().trim().isEmpty()) {
                errorText.setVisibility(View.VISIBLE);
                errorText.setText(R.string.please_fill_all_fields);
            } else {
                errorText.setVisibility(View.GONE);
                progressBarLogin.setVisibility(View.VISIBLE);
                loginUser(emailInput.getText().toString().trim(), passwordInput.getText().toString().trim());

            }

        });
    }

    private void loginUser(String email, String password) {
        String url = "https://dev4.pristinefulfil.com/api/UserLogin/Login";
        progressBarLogin.setVisibility(View.VISIBLE);

        RequestQueue volleyQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    progressBarLogin.setVisibility(View.GONE);
                    errorText.setVisibility(View.GONE);

                    try {
                        Log.d("API_RESPONSE", response);

                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() > 0) {
                            JSONObject userObject = jsonArray.getJSONObject(0);

                            Toast.makeText(PostVolley.this, "Welcome, " + userObject.getString("name"), Toast.LENGTH_SHORT).show();

                            clearFields();
                        } else {
                            Toast.makeText(PostVolley.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        errorText.setText(R.string.email_or_password_is_incorrect);
                        errorText.setVisibility(View.VISIBLE);
                    }
                },
                error -> {
                    progressBarLogin.setVisibility(View.GONE);
                    Log.e("VolleyErrors", "Error: " + error.toString());
                    Toast.makeText(PostVolley.this, "Failed to get response: " + error.toString(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("email", email);
                    requestBody.put("password", password);
                } catch (JSONException e) {
                    Log.e("VolleyErrors", "Error: " + e.getMessage());
                }
                return requestBody.toString().getBytes();
            }
        };

        volleyQueue.add(stringRequest);
    }

    private void clearFields() {
        emailInput.setText("");
        passwordInput.setText("");
        emailInput.requestFocus();
        emailInput.hasFocus();
    }


    private void initViews() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signInBtn = findViewById(R.id.signInBtn);
        progressBarLogin = findViewById(R.id.progrssBarLogin);
        errorText = findViewById(R.id.errorText);
    }
}
