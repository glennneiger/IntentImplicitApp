package com.fiqri.intentimplicitapp;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btnRing)
    Button btnRing;
    @BindView(R.id.btnSilent)
    Button btnSilent;
    @BindView(R.id.btnVibrate)
    Button btnVibrate;
    @BindView(R.id.btnMute)
    Button btnMute;
    @BindView(R.id.btnMode)
    Button btnMode;

    AudioManager audio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);

        audio = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @OnClick({R.id.btnRing, R.id.btnSilent, R.id.btnVibrate, R.id.btnMute, R.id.btnMode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRing:
                audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                break;
            case R.id.btnSilent:
                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                break;
            case R.id.btnVibrate:
                audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                break;
            case R.id.btnMute:
                audio.setRingerMode(AudioManager.ADJUST_MUTE);
                break;
            case R.id.btnMode:

                audio.getRingerMode();

//                switch (audio.getRingerMode()) {
//                    case AudioManager.RINGER_MODE_NORMAL:
//                    toast("Mode Normal");
//                    break;
//                    case AudioManager.RINGER_MODE_SILENT:
//                        toast("Mode Diam");
//                        break;
//                    case AudioManager.RINGER_MODE_VIBRATE:
//                        toast("Mode Getar");
//                        break;
//                    case AudioManager.ADJUST_MUTE:
//                        break;
//                }

                if (audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
                    Toast.makeText(this, "Mode Normal", Toast.LENGTH_SHORT).show();
                }
                else if (audio.getRingerMode() == AudioManager.RINGER_MODE_SILENT){
                    Toast.makeText(this, "Mode Diam", Toast.LENGTH_SHORT).show();
                }
                else if (audio.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE){
                    Toast.makeText(this, "Mode Getar", Toast.LENGTH_SHORT).show();
                }
                else if (audio.getRingerMode() == AudioManager.ADJUST_MUTE){
                    Toast.makeText(this, "Dalam mode Mute", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
//    private void toast(String p) {
//        Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
//    }
//}
