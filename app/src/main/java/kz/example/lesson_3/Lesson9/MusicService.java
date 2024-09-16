package kz.example.lesson_3.Lesson9;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import kz.example.lesson_3.R;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;
    private final IBinder binder = new MusicBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.bring_me_the_horizon);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null){
            switch (intent.getAction()){
                case "ACTION_PLAY":
                    playMusic();
                    break;
                case "ACTION_PAUSE":
                    pauseMusic();
                    break;
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public void playMusic(){
        if(mediaPlayer != null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public boolean isPlaying(){
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public void pauseMusic(){
        if(isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MusicBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;
        }
    }



}
