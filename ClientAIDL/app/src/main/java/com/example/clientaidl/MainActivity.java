package com.example.clientaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.serveraidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private TextView displayText;
    private Button messageButtom;

    private int value = -1;
    private static final String TAG = "MainActivity";

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        displayText = findViewById(R.id.displayText);
        messageButtom = findViewById(R.id.display_message_button);

        messageButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int color = iMyAidlInterface.getColor();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            boolean status = true;
                            if (status) {
                                status = false;
                                displayText.setText(color + "color value ");
                                displayText.setBackgroundColor(color);
                            } else {
                                status = true;
                                displayText.setText(color + "color value ");
                                displayText.setBackgroundColor(color);
                            }
                        }
                    });
                } catch (RemoteException e) {
                    Log.e("Client", "RemoteException: " + e);
                }

            }
        });
        // Service bind logic


        Intent intent = new Intent();
        intent.setAction("com.example.serveraidl.IMyAidlInterface");
        intent.setPackage("com.example.serveraidl");
        bindService(intent, serviceClinet, BIND_AUTO_CREATE);


    }

    private ServiceConnection serviceClinet = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);

            if (iMyAidlInterface != null) {
                try {
                    int color = iMyAidlInterface.getColor();
//                    value = color;
                    Log.d(TAG, "onServiceConnected: lavkush" + color);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "" + color, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        unbindService(intent, serviceClinet, BIND_AUTO_CREATE);

    }
}