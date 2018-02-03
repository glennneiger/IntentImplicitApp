package com.fiqri.intentimplicitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btnBluetooth)
    Button btnBluetooth;
    @BindView(R.id.btnWifi)
    Button btnWifi;
    @BindView(R.id.btnAudioManager)
    Button btnAudioManager;
    @BindView(R.id.btnAudioRecord)
    Button btnAudioRecord;
    @BindView(R.id.btnTextTospeech)
    Button btnTextTospeech;
    @BindView(R.id.btnNotif)
    Button btnNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnBluetooth, R.id.btnWifi, R.id.btnAudioManager, R.id.btnAudioRecord, R.id.btnTextTospeech, R.id.btnNotif})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btnBluetooth:
                startActivity(new Intent(this, BluetoothActivity.class));
                break;
            case R.id.btnWifi:
                startActivity(new Intent(this, WifiActivity.class));
                break;
            case R.id.btnAudioManager:
                startActivity(new Intent(this, AudioManagerActivity.class));
                break;
            case R.id.btnAudioRecord:
                startActivity(new Intent(this, AudioRecordActivity.class));
                break;
            case R.id.btnTextTospeech:
                startActivity(new Intent(this, TextToSpeechActivity.class));
                break;
            case R.id.btnNotif:
                startActivity(new Intent(this, NotifActivity.class));
                break;
        }
    }
}
