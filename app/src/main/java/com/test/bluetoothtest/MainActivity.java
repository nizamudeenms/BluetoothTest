package com.test.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    int BLUETOOTH_REQUEST = 1;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bluetooth_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                if (bluetoothAdapter != null) {
                    if (!bluetoothAdapter.isEnabled()) {
                        Toast.makeText(MainActivity.this, "Adapter Available", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, BLUETOOTH_REQUEST);
                    } else {
                        bluetoothAdapter.disable();
                        button.setText("Click to Enable BlueTooth");
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Adapter Unavailable", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == BLUETOOTH_REQUEST) {
            if (resultCode == RESULT_OK) {
                button.setText("Click to Disable BlueTooth");
                Toast.makeText(this, "BLuetooth Switched on", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Bluttooth Failed switching on", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
