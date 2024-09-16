package kz.example.lesson_3.Lesson9;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.R;

public class MainActivity_Lesson9 extends AppCompatActivity {

    private MusicService musicService;
    private boolean isBound = false;
    private Button startBtn;
    private Button stopBtn;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            isBound = true;
            updateButtons();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    private void updateButtons() {
        if (musicService.isPlaying()) {
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
        } else {
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson9);

        startBtn = findViewById(R.id.btn_startMusic_less9);
        stopBtn = findViewById(R.id.btn_stopMusic_less9);


        ImageView imageView = findViewById(R.id.imageView_less9);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_less9);
        fadeInAnimation.setRepeatCount(Animation.INFINITE);

        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        startBtn.setOnClickListener(v -> {
            if(isBound){
                imageView.startAnimation(fadeInAnimation);
                musicService.playMusic();
                updateButtons();
            }
        });

        stopBtn.setOnClickListener(v -> {
            if(isBound){
                imageView.clearAnimation();
                musicService.pauseMusic();
                updateButtons();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBound){
            unbindService(serviceConnection);
            isBound = false;
        }
    }
}