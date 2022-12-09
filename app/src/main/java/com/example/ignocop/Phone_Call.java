package com.example.ignocop;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Phone_Call extends AppCompatActivity {

    MediaPlayer player;
    String messageToSend ;
    String number ;
    ImageButton sendButton;
    private static final int REQUEST_SMS = 0;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__call);
        //sendButton = (ImageButton) findViewById(R.id.imageButton4);

        if (player == null) {
            player = MediaPlayer.create(this, R.raw.song);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }


        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSend = "this is a message";
                String number = "+919449896447";
                SmsManager sms = SmsManager.getDefault();

                sms.getDefault().sendTextMessage(number, null, messageToSend, null,null);
                Toast.makeText(Phone_Call.this, "NOTIFICATION SENT", Toast.LENGTH_SHORT).show();
                stopPlayer();


            }
        });

        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Phone_Call.this, "SWITCHED OFF ALARM", Toast.LENGTH_SHORT).show();
                stopPlayer();


            }
        });

       /* sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSend = "this is a message";
                String number = "+919742143483";
                SmsManager sms = SmsManager.getDefault();

                sms.getDefault().sendTextMessage(number, null, messageToSend, null,null);

                stopPlayer();
            }
        });*/
    }




    public void receive(View view) {
        Toast.makeText(Phone_Call.this, "Switched OFF ALARM", Toast.LENGTH_SHORT).show();
        stopPlayer();

    }

    public void cutcall(View view) {
        Toast.makeText(Phone_Call.this, "Notify Neighbors", Toast.LENGTH_SHORT).show();
        stopPlayer();
        messageToSend = "this is a message";
        number = "+918123921957";

        SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);

    }


    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
