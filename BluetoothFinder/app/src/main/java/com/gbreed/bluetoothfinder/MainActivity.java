package com.gbreed.bluetoothfinder;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("Action", action);
            if(bluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                statusTextView.setText("");
                searchButton.setEnabled(true);
            }else if(BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                String address = device.getAddress();
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);

                String output = "";

                if(name.equals(""))
                {
                    output = "Address: " + address + " RSSI: " + Integer.toString(rssi);
                }else
                {
                    output = "Name: " + name + " Address: " + address + " RSSI: " + Integer.toString(rssi);
                }

                Log.i("Found", output);
                deviceList.add(output);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };

    BluetoothAdapter bluetoothAdapter;
    ListView listView;
    TextView statusTextView;
    Button searchButton;
    ArrayList<String> deviceList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        statusTextView = findViewById(R.id.textViewStatus);
        searchButton = findViewById(R.id.buttonSearch);
        searchButton.setEnabled(false);

        deviceList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deviceList);
        listView.setAdapter(arrayAdapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver, intentFilter);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableBlue = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlue, 8);
        }else
        {
            searchButton.setEnabled(true);
        }
    }

    public void searchClicked(View view)
    {
        deviceList.clear();
        arrayAdapter.notifyDataSetChanged();
        statusTextView.setText("Searching...");
        searchButton.setEnabled(false);
        bluetoothAdapter.startDiscovery();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 8)
        {
            if(resultCode == RESULT_OK)
            {
                searchButton.setEnabled(true);
            }
        }
    }
}
