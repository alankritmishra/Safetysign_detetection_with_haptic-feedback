// Copyright (c) 2020 Facebook, Inc. and its affiliates.
// All rights reserved.
//
// This source code is licensed under the BSD-style license found in the
// LICENSE file in the root directory of this source tree.

package ca.lakeheadu.cv.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import org.pytorch.LiteModuleLoader;
import org.pytorch.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int mImageIndex = 0;
    private Bitmap mBitmap = null;
    private Module mModule = null;
    Switch hapticSwitch;
    Switch speechSwitch;
    public static boolean hapticFeedback = true;
    public static boolean speechFeedback = true;

    Vibrator vibrator;
    VibrationEffect vibrationEffect1;
    TextToSpeech tts;


    public static String assetFilePath(Context context, String assetName) throws IOException {
        File file = new File(context.getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        //setting up vibrator and tts
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        vibrationEffect1 = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                    tts.setSpeechRate(0.8f);
                }
            }
        });


        setContentView(R.layout.activity_main);

        // Haptic Switch
        hapticSwitch = (Switch) findViewById(R.id.hapticSwitch);
        hapticSwitch.setChecked(hapticFeedback);
        hapticSwitch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (hapticSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Haptic feedback activated.", Toast.LENGTH_SHORT).show();
                    hapticFeedback = true;
                    vibrator.cancel();
                    vibrator.vibrate(vibrationEffect1);
                } else {
                    Toast.makeText(getApplicationContext(), "Haptic feedback deactivated.", Toast.LENGTH_SHORT).show();
                    hapticFeedback = false;
                }
            }
        });

        // Speech Switch
        speechSwitch = (Switch) findViewById(R.id.speechSwitch);
        speechSwitch.setChecked(speechFeedback);
        speechSwitch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (speechSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Speech feedback activated.", Toast.LENGTH_SHORT).show();
                    speechFeedback = true;
                    tts.speak("Speech feedback activated.", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    Toast.makeText(getApplicationContext(), "Speech feedback deactivated.", Toast.LENGTH_SHORT).show();
                    speechFeedback = false;
                }
            }
        });

        final Button buttonLive = findViewById(R.id.launchCamera);
        buttonLive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, ObjectDetectionActivity.class);
                startActivity(intent);
            }
        });


        try {
            mModule = LiteModuleLoader.load(MainActivity.assetFilePath(getApplicationContext(), "best.torchscript.ptl"));
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("classes.txt")));
            String line;
            List<String> classes = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                classes.add(line);
            }
            PrePostProcessor.mClasses = new String[classes.size()];
            classes.toArray(PrePostProcessor.mClasses);
        } catch (IOException e) {
            Log.e("Object Detection", "Error reading assets", e);
            finish();
        }
    }


}
