package com.first.headerretrofit;

import android.os.Bundle;
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

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button getData;
    TextView firstName,middleName,lastName,emailId,empId,compId,state;
    LinearLayout details;
    TextInputEditText emailInput;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getData.setOnClickListener(v -> findUser());
    }

    private void findUser() {
        progressBar.setVisibility(View.VISIBLE);
        String email=emailInput.getText().toString();
        if (emailInput.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        }
        UserData userData=new UserData(email);
        RetrofitInstance.getApiService().getDetails(userData).enqueue(new Callback<List<ResponseData>>() {
            @Override
            public void onResponse(Call<List<ResponseData>> call, Response<List<ResponseData>> response) {
                if (response.isSuccessful() && response.body()!=null){
                    ResponseData responseData=response.body().get(0);
                    if (userData.getEmail_id().equalsIgnoreCase(responseData.getLogin_email_id())){
                        showDetails(responseData);
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        clearInput();
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Email Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResponseData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void showDetails(ResponseData responseData) {
        firstName.setText(responseData.getFirst_name());
        middleName.setText(responseData.getMiddle_name());
        lastName.setText(responseData.getLast_name());
        emailId.setText(responseData.getLogin_email_id());
        empId.setText(responseData.getEmp_id());
        compId.setText(responseData.getCompany_id());
        state.setText(responseData.getState());
        details.setVisibility(View.VISIBLE);
        getData.setText(R.string.get_other);
    }

    private void clearInput() {
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