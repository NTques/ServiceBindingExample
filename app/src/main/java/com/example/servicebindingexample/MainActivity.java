package com.example.servicebindingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.servicebindingexample.databinding.ActivityMainBinding;
import com.example.servicebindingexample.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null;

    MyService ms;
    boolean isService = false;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder mb = (MyBinder) service;
            ms = mb.getService();

            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

        binding.btnReceiveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                int num = ms.getRan();
                binding.textView.setText(num + "");
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}