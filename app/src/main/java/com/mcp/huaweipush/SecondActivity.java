package com.mcp.huaweipush;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private TextView mLogText;
    private Switch mTranscriptionsSwitch;
    private Switch mStoreSamplesSwitch;
    private Switch mDeepSpeechSwitch;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initialize() {
        mTranscriptionsSwitch = findViewById(R.id.switchTranscriptions);
        mStoreSamplesSwitch = findViewById(R.id.switchSamples);
        mDeepSpeechSwitch = findViewById(R.id.useDeepSpeech);

        List<String> permissions = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.RECORD_AUDIO);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissions.size() > 0) {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[0]), 123);
        }

        mStartButton = findViewById(R.id.button_start);

        mLogText = findViewById(R.id.plain_text_input);
        mLogText.setMovementMethod(new ScrollingMovementMethod());



        findViewById(R.id.button_clear).setOnClickListener(v -> mLogText.setText(""));

        mTranscriptionsSwitch.toggle();
        mStoreSamplesSwitch.toggle();
        mDeepSpeechSwitch.toggle();

    }

}
