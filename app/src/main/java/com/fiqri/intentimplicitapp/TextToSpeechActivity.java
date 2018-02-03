package com.fiqri.intentimplicitapp;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    @BindView(R.id.texttts)
    EditText texttts;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ButterKnife.bind(this);

        tts = new TextToSpeech(this,this);
    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(texttts.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }

    @Override
    public void onInit(int status) {

        if (status != 0){

            Locale bahasa = Locale.getDefault();
            tts.setLanguage(bahasa);
        }

    }
}
