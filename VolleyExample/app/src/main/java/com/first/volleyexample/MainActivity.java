package com.first.volleyexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    LinearLayout detailsArea;
    TextView name, userId, userEmail, userType;
    Button getDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        getDataBtn.setOnClickListener(v->getDetails());
    }

    private void getDetails() {
        RequestQueue volleyQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://wmsmarketplacedemo.pristinefulfil.com/api/User/EmployeeDetailGet?user_id=SP-521";

        JsonArrayRequest userDataRequest=new JsonArrayRequest(
                Request.Method.GET,url,null,response->{
                    if (response!=null) {
                        try {
                            JSONObject jsonOnj = response.getJSONObject(0);
                            name.setText(jsonOnj.getString("name"));
                            userId.setText(jsonOnj.getString("user_id"));
                            userEmail.setText(jsonOnj.getString("email_id"));
                            userType.setText(jsonOnj.getString("user_type"));
                            detailsArea.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
        },(Response.ErrorListener) error -> {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
    });
        volleyQueue.add(userDataRequest);
    }

    private void initViews() {
        detailsArea = findViewById(R.id.detailsArea);
        name = findViewById(R.id.userName);
        userId = findViewById(R.id.companyId);
        userEmail = findViewById(R.id.userEmail);
        getDataBtn = findViewById(R.id.getDataBtn);
        userType = findViewById(R.id.userType);
    }
}