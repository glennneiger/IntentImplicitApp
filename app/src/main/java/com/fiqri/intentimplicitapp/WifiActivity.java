package com.fiqri.intentimplicitapp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends AppCompatActivity {

    @BindView(R.id.switchWifi)
    Switch switchWifi;
    @BindView(R.id.txtWifi)
    TextView txtWifi;

    WifiManager mWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
        
        mWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (mWifi.isWifiEnabled()){
            switchWifi.setChecked(true);
        }
        else{
            switchWifi.setChecked(false);
        }
        
        switchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                
                if (b == true){

                    //aktif wifi
                    wifi(true);
                }
                else {
                    //wifi no aktif
                    wifi(false);
                }
            }
        });
    }

    private void wifi(boolean b) {
        mWifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        //check wifi aktif apa enggak
        if (b == true && !mWifi.isWifiEnabled()) {

            //mengatifkan wifi kalau tidak aktif
            mWifi.setWifiEnabled(true);

            Toast.makeText(this, "Wifi ON", Toast.LENGTH_SHORT).show();
        }
        else if (b == false && mWifi.isWifiEnabled()){

             mWifi.setWifiEnabled(false);

            Toast.makeText(this, "Wifi OFF", Toast.LENGTH_SHORT).show();
         }
     }
}
