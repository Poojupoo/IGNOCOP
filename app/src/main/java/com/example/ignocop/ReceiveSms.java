package com.example.ignocop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    String body = null;
    String no = "+919449896447";
    String msgBody=null;
    String msg = "Danger";


    @Override
    public void onReceive(Context context, Intent intent) {

        String address1 = null;

        Bundle bundle = intent.getExtras();

        String msg_from = null;

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                msg_from=messages[i].getOriginatingAddress();
                address1 = messages[i].getOriginatingAddress();
                msgBody = messages[i].getMessageBody();


            }

            if (msg_from.equals(no) && msgBody.equals(msg)) {
                Intent open = new Intent(context, Phone_Call.class);
                open.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(open);
            }

            Toast.makeText(context, "Received" + msg_from + " ", Toast.LENGTH_SHORT).show();
        }
    }
}
