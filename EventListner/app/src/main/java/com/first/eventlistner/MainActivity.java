package com.first.eventlistner;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText number1, number2;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       number1=findViewById(R.id.number1);
       number2=findViewById(R.id.number2);
       result=findViewById(R.id.result);
       number1.addTextChangedListener(textWatcher);
       number2.addTextChangedListener(textWatcher);
    }
    private TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String x = number1.getText().toString().trim();
            String y = number2.getText().toString().trim();

                if (x.equalsIgnoreCase("")) {
                    x = "0";
                }
                if (y.equalsIgnoreCase("")) {
                    y = "0";
                }

                long x1 = Long.parseLong(x);
                long y2 = Long.parseLong(y);
                result.setText(String.valueOf(x1 + y2));
            }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}