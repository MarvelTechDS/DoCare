package com.marveltech.docare;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.VolumeProvider;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import androidx.media.VolumeProviderCompat;
import androidx.core.view.KeyEventDispatcher;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static android.view.KeyEvent.KEYCODE_VOLUME_UP;
import static android.view.KeyEvent.keyCodeFromString;

public class BackgroundService extends Service {
    MediaSessionCompat mediaSession;
    int count = 0;
    FirebaseAuth auth;
    String uid;
    String n1,n2,n3;
    public BackgroundService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onTaskRemoved(intent);
        n1 = HomeActivity2.contactnumber1;
        n2 = HomeActivity2.contactnumber2;
        n3 = HomeActivity2.contactnumber3;
        uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
//        detectSms();
        mediaSession = new MediaSessionCompat(this, "PlayerService");
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSession.setPlaybackState(new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_PLAYING, 0, 0) //you simulate a player which plays something.
                .build());
        VolumeProviderCompat myVolumeProvider =
                new VolumeProviderCompat(VolumeProviderCompat.VOLUME_CONTROL_RELATIVE, /*max volume*/100, /*initial volume level*/95) {
                    @Override
                    public void onAdjustVolume(int direction) {
                        if (direction==1) {
                            count++;
                        }
                        if (count>= 3){
                            Toast.makeText(BackgroundService.this, "trigger", Toast.LENGTH_SHORT).show();
                            count=0;
                            openactivity5();
                        }
                /*
                -1 -- volume down
                1 -- volume up
                0 -- volume button released
                 */
                    }
                };

        mediaSession.setPlaybackToRemote(myVolumeProvider);
        mediaSession.setActive(true);

        return START_STICKY;
    }

    private void detectSms() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"),null,null,null,null);
        cursor.moveToFirst();
        while (cursor != null)
        {
            String msg = cursor.getString(12);
            if (msg.contains("Emergency Need Help    -----------From DoCare"))
            {
                alarm();
            }
        }
    }

    private void alarm() {

    }

    private void openactivity5() {
       sendSMS1();
       sendSMS2();
       sendSMS3();
       call();
    }

    private void call() {
        Intent  i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse(n1));
    }

    private void sendSMS3() {
        SmsManager smsManager1 = SmsManager.getDefault();
        smsManager1.sendTextMessage(n1,null,"Emergency Need Help    -----------From DoCare",null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

    private void sendSMS2() {
        SmsManager smsManager2 = SmsManager.getDefault();
        smsManager2.sendTextMessage(n2,null,"Emergency Need Help    -----------From DoCare",null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

    private void sendSMS1() {
        SmsManager smsManager3 = SmsManager.getDefault();
        smsManager3.sendTextMessage(n3,null,"Emergency Need Help     -----------From DoCare",null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartIntent = new Intent(getApplicationContext(),this.getClass());
        restartIntent.setPackage(getPackageName());
        startService(restartIntent);
        super.onTaskRemoved(rootIntent);
    }


}