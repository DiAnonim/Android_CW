package kz.example.lesson_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.Lesson10.MainActivity_Lesson10;
import kz.example.lesson_3.Lesson4.Lesson4Activity1;
import kz.example.lesson_3.Lesson5.Lesson5Activity1;
import kz.example.lesson_3.Lesson6.Lesson6_Activity1;
import kz.example.lesson_3.Lesson6_ClassWork.Less6_CW1;
import kz.example.lesson_3.Lesson6_ClassWork.SplashActivity;
import kz.example.lesson_3.Lesson7.Lesson7Main;
import kz.example.lesson_3.Lesson8.MainActivity_Lesson8;
import kz.example.lesson_3.Lesson9.MainActivity_Lesson9;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void ClickMenuButton(View view){
        Intent intent = new Intent(MainActivity.this, SplashActivityCalc.class);
        startActivity(intent);
    }

    public void onClickLesson4(View view){
        Intent intent = new Intent(MainActivity.this, Lesson4Activity1.class);
        startActivity(intent);
    }

    public void onClickLesson5(View view){
        Intent intent = new Intent(MainActivity.this, Lesson5Activity1.class);
        startActivity(intent);
    }

    public void onClickLesson6(View view){
        Intent intent = new Intent(MainActivity.this, Lesson6_Activity1.class);
        startActivity(intent);
    }

    public void onClickLesson6CW(View view){
        Intent intent = new Intent(MainActivity.this, Less6_CW1.class);
        startActivity(intent);
    }

    public void onClickLesson7(View view){
        Intent intent = new Intent(MainActivity.this, Lesson7Main.class);
        startActivity(intent);
    }

    public void onClickLesson8(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity_Lesson8.class);
        startActivity(intent);
    }

    public void onClickLesson9(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity_Lesson9.class);
        startActivity(intent);
    }

    public void onClickLesson10(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity_Lesson10.class);
        startActivity(intent);
    }
}