package com.first.headerretrofit.volleyy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.first.headerretrofit.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyHeader extends AppCompatActivity {
    Button getData;
    TextView firstName,middleName,lastName,emailId,empId,compId,state;
    LinearLayout details;
    TextInputEditText emailInput;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_header);
        initViews();

        getData.setOnClickListener(v -> {
            if (emailInput.getText().toString().equalsIgnoreCase("")){
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            }else findUser(emailInput.getText().toString());
        });

    }

    private void findUser(String email) {
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://dev4.pristinefulfil.com/api/Employee/EmployeeDetailGet";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> apiResponse(response), error -> errorResponse(error))
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("company_id", "Green Gold Seeds Pvt. Ltd.");
                return headers;
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("email_id", email);
                } catch (JSONException e) {
                    Toast.makeText(VolleyHeader.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("VolleyErrors", "Error: " + e.getMessage());
                }
                return requestBody.toString().getBytes();
            }
        };
        requestQueue.add(stringRequest);
    }

    private void errorResponse(VolleyError error) {
            Toast.makeText(VolleyHeader.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
    }

    private void apiResponse(String response) {
        {
            if (response != null){
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() > 0) {
                        JSONObject jobj = jsonArray.getJSONObject(0);
                        setData(jobj);
                    } else {
                        Toast.makeText(VolleyHeader.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(VolleyHeader.this, "Email not found!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setData(JSONObject jobj) throws JSONException {
        firstName.setText(jobj.getString("first_name"));
        middleName.setText(jobj.getString("middle_name"));
        lastName.setText(jobj.getString("last_name"));
        emailId.setText(jobj.getString("login_email_id"));
        empId.setText(jobj.getString("emp_id"));
        compId.setText(jobj.getString("company_id"));
        state.setText(jobj.getString("state"));
        details.setVisibility(View.VISIBLE);
        getData.setText(R.string.get_other);
        clearFields();
    }

    private void clearFields() {
        emailInput.setText("");
    }

    private void initViews() {
        getData=findViewById(R.id.getData);
        details=findViewById(R.id.details);
        emailInput=findViewById(R.id.emailInput);
        progressBar=findViewById(R.id.progrssBar);
        firstName=findViewById(R.id.firstName);
        middleName=findViewById(R.id.middleName);
        lastName=findViewById(R.id.lastName);
        emailId=findViewById(R.id.userEmail);
        empId=findViewById(R.id.empId);
        compId=findViewById(R.id.companyId);
        state=findViewById(R.id.state);
    }
}