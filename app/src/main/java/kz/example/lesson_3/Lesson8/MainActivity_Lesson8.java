package kz.example.lesson_3.Lesson8;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import kz.example.lesson_3.R;

public class MainActivity_Lesson8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson8);

        ImageView imageView = findViewById(R.id.imageView_less8);

        Picasso.get().load("https://i.pinimg.com/originals/8d/1d/65/8d1d6526b07feb3bbf86820caf7b441f.jpg").into(imageView);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.complex_animation);
        imageView.startAnimation(fadeInAnimation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}