package com.example.ignocop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;
    ImageView hat;
    TextView textView;
    Animation frombottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, ExampleService.class);

        ContextCompat.startForegroundService(this, serviceIntent);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS},
                MY_PERMISSIONS_REQUEST_SMS_RECEIVE);

       // Intent serviceIntent = new Intent(this, ForegroundService.class);
       // serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

       // ContextCompat.startForegroundService(this, serviceIntent);


        hat=(ImageView) findViewById(R.id.imgView_logo);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        hat.setAnimation(frombottom);
        textView=(TextView) findViewById(R.id.textView);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        textView.setAnimation(frombottom);

        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
            }
        },2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SMS_RECEIVE) {
            // YES!!
            Log.i("TAG", "MY_PERMISSIONS_REQUEST_SMS_RECEIVE --> YES");
        }
    }
}
