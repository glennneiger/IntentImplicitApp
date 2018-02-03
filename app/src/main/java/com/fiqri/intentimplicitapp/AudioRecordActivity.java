package com.fiqri.intentimplicitapp;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecordActivity extends AppCompatActivity {

    @BindView(R.id.btnRecord)
    Button btnRecord;
    @BindView(R.id.btnStopRecord)
    Button btnStopRecord;
    @BindView(R.id.btnPlay)
    Button btnPlay;

    MediaRecorder mRecorder;
    String mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecord.3gp";

        btnRecord.setEnabled(true);
        btnStopRecord.setEnabled(false);
        btnPlay.setEnabled(false);
    }

    @OnClick({R.id.btnRecord, R.id.btnStopRecord, R.id.btnPlay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRecord:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},1);

                    initMediaRecord();
                    try {
                        mRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mRecorder.start();
                }

                btnRecord.setEnabled(false);
                btnStopRecord.setEnabled(true);
                btnPlay.setEnabled(true);

                break;

            case R.id.btnStopRecord:
                try{
                    mRecorder.stop();
                }
                catch (IllegalStateException e){
                    e.printStackTrace();
                }

                mRecorder.release();
                btnPlay.setEnabled(true);
                btnRecord.setEnabled(true);

                break;

            case R.id.btnPlay:
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(mFileName);
                }catch (IOException e){
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }
                    mediaPlayer.start();
                break;
        }
    }

    private void initMediaRecord() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }
}
