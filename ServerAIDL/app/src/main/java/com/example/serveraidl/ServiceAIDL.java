package com.example.serveraidl;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class ServiceAIDL extends Service {

    private static final String TAG = "ServiceAIDL";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final  IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public int getColor() throws RemoteException {

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomColor = Color.rgb(r,g,b);
            Log.d(TAG, "getColor: " +randomColor);
            return randomColor;
        }
    };

}
