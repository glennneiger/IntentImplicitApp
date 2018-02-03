package com.fiqri.intentimplicitapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BluetoothActivity extends AppCompatActivity {


    private static final int REQUEST_ENABLE_BT = 1;

    BluetoothAdapter mBluetoothAdapter;

    @BindView(R.id.btnDisable)
    Button btnDisable;
    @BindView(R.id.btnEnable)
    Button btnEnable;
    @BindView(R.id.btnListPaired)
    Button btnListPaired;
    @BindView(R.id.btnDiscoverDevice)
    Button btnDiscoverDevice;
    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);

        //initialisasi bluetooth
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @OnClick({R.id.btnDisable, R.id.btnEnable, R.id.btnListPaired, R.id.btnDiscoverDevice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDisable:

                gaAktif();
                break;
            case R.id.btnEnable:
                aktif();
                break;
            case R.id.btnListPaired:
                paired();
                break;
            case R.id.btnDiscoverDevice:
                discover();
                break;
        }
    }

    private void discover(){
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    private void paired() {

        //set informasi bluetooth
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        //check paired
        if (pairedDevices.size()> 0){
            //seandainya ada kita looping
            ArrayList marrayAdapter = new ArrayList();
            for (BluetoothDevice device : pairedDevices){

                marrayAdapter.add(device.getName() +"\n" + device.getAddress());
            }

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, marrayAdapter);
            listview.setAdapter(adapter);
        }

    }

    private void gaAktif() {
        if (mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.disable();
        }
        else {

            Toast.makeText(this, "Sudah tidak aktif", Toast.LENGTH_SHORT).show();
        }

    }

    private void aktif() {
        //check dari bluetooth aktif apa enggak
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            Toast.makeText(this, "Sudah Aktif", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != 0 && requestCode == REQUEST_ENABLE_BT) {

            Toast.makeText(this, "Udah Aktif", Toast.LENGTH_SHORT).show();
        }
    }
}
