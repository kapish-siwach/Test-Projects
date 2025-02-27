package com.first.services;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button start,stop;
    AirplaneModeChangeReceiver airreceiver = new AirplaneModeChangeReceiver();
    BluetoothReceiver btreceiver = new BluetoothReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.startService);
        stop=findViewById(R.id.stopService);

       start.setOnClickListener(view->{
           startService(new Intent(this,NewService.class));
       });

       stop.setOnClickListener(view->{
           stopService(new Intent(this,NewService.class));
       });
       }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airreceiver,filter);

        IntentFilter filter1=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(btreceiver,filter1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            unregisterReceiver(airreceiver);
            unregisterReceiver(btreceiver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
