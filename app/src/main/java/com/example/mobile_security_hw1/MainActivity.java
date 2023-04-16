package com.example.mobile_security_hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText pass;

    // Declare the system services
    ConnectivityManager connectivityManager;
    WifiManager wifiManager;
    BluetoothAdapter bluetoothAdapter;
    BatteryManager batteryManager;
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        pass = findViewById(R.id.pass);

        // Initialize the system services
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        metrics = getResources().getDisplayMetrics();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pass.getText().toString();
                checkLogin(password);
            }
        });
    }

    //provide information from the device
    private boolean isNetworkConnected() {
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private boolean isWiFiEnabled() {
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    private boolean isBluetoothEnabled() {
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    private int batteryLevel() {
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    private int screenHeight() {
        return metrics.heightPixels;
    }

    private int screenWidth() {
        return metrics.widthPixels;
    }

    //Navigate from the main activity to the home activity
    private void showHomeScreen() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    //Display an appropriate error message to the user if login fails
    private void showErrorMessage() {
        if(!isWiFiEnabled() && !isNetworkConnected()){
            Toast.makeText(this, "You Can't Access, Network is not connected", Toast.LENGTH_SHORT).show();
        } else if (!isWiFiEnabled()) {
            Toast.makeText(this, "You Can't Access, Wi-Fi is not enabled", Toast.LENGTH_SHORT).show();
        } else if (!isNetworkConnected()) {
            Toast.makeText(this, "You Can't Access,Network is not connected", Toast.LENGTH_SHORT).show();
        } else if (!isBluetoothEnabled()) {
            Toast.makeText(this, "You Can't Access, Bluetooth is not enabled", Toast.LENGTH_SHORT).show();
        } else if (batteryLevel() < 100) {
            Toast.makeText(this, "You Can't Access,Battery Level under 100", Toast.LENGTH_SHORT).show();
        } else if (screenHeight() < 1000 || screenWidth() < 1000) {
            Toast.makeText(this, "You Can't Access,Screen size under 1000 pixels", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login unsuccessful. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    // Define the login approval logic
    private void checkLogin(String password) {
        if (password.equals(getString(R.string.battery_password))) {
            if(batteryLevel() == 100) {
                // Battery level condition is satisfied, continue to login
                    showHomeScreen();
            }else{
                    Toast.makeText(this, "You Can't Access,Battery Level under 100", Toast.LENGTH_SHORT).show();
                }

        } else if (password.equals(getString(R.string.wifi_password))) {
            if (isWiFiEnabled()){
                // Wifi condition is satisfied, continue to login
                    showHomeScreen();
            }else {
                Toast.makeText(this, "You Can't Access, Wi-Fi is not enabled", Toast.LENGTH_SHORT).show();
            }

        } else if (password.equals(getString(R.string.screen_size_password))) {
            // Screen size condition is satisfied, continue to login
            if(screenHeight() >= 1000 && screenWidth() >= 1000) {
                    showHomeScreen();
            }else{
                Toast.makeText(this, "You Can't Access,Screen size under 1000 pixels", Toast.LENGTH_SHORT).show();
            }

        } else if (password.equals(getString(R.string.bluetooth_password))) {
            if (isBluetoothEnabled()) {
                // Bluetooth condition is satisfied, continue to login
                    showHomeScreen();
            }else{
                Toast.makeText(this, "You Can't Access, Bluetooth is not enabled", Toast.LENGTH_SHORT).show();
            }

        } else if (password.equals(getString(R.string.network_password))) {
            if(isNetworkConnected() && !isWiFiEnabled()) {
                // Network condition is satisfied, continue to login
                    showHomeScreen();
            }else{
                Toast.makeText(this, "You Can't Access,Network is not connected", Toast.LENGTH_SHORT).show();
            }
        } else if (password.equals(getString(R.string.all_conditions_password))) {
            // Condition the login approval on the device parameters
            if (isWiFiEnabled() && isNetworkConnected() && isBluetoothEnabled() && batteryLevel() == 100 && screenHeight() >= 1000 && screenWidth() >= 1000) {
                // Login is approved
                showHomeScreen();
            } else {
                // Login is rejected
                showErrorMessage();
            }
        } else {
            // Entered password is not valid
            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
        }
    }



      // authenticate a user's password
//    private boolean authenticate(String password) {
//        return password.equals("all");
//    }
    //    public void login(String password) {
//        // Authenticate the user
//        boolean isAuthenticated = authenticate(password);
//
//        // Condition the login approval on the device parameters
//        if (isWiFiEnabled() && isNetworkConnected() && isBluetoothEnabled() && batteryLevel() == 100 && screenHeight() >= 1000 && screenWidth() >= 1000 && isAuthenticated) {
//            // Login is approved
//            showHomeScreen();
//        } else {
//            // Login is rejected
//            showErrorMessage();
//        }
//    }

}


