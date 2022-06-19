package com.example.servicebindingexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class MyService extends Service {

    IBinder mBinder = new MyBinder();

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    int getRan() {
        return new Random().nextInt();
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "서비스 실행됨", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),"서비스 종료됨", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}