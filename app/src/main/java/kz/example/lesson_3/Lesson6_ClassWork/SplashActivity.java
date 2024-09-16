package kz.example.lesson_3.Lesson6_ClassWork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import kz.example.lesson_3.MainActivity;
import kz.example.lesson_3.R;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imageView_splash_less6);

        Picasso.get().load("https://avatars.mds.yandex.net/i?id=56756984490693256571bc2f039a407f1082ac7e-3823851-images-thumbs&n=13").into(imageView);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.complex_animation);
        imageView.startAnimation(fadeInAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}
