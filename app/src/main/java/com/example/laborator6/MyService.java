package com.example.laborator6;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    public MyLocalBinder localBinder = new MyLocalBinder();
    MediaPlayer mediaPlayer;
    Timer timer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class MyLocalBinder extends Binder {
        MyService getBoundService() {
            return MyService.this;
        }
    }

    public void getSound(int d, int v) {
        String audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.setVolume(v, v);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //durata de desfasurare a sunetului
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        }, d);

    }
}
