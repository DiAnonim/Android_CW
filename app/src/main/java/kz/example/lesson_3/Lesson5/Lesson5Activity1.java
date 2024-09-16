package kz.example.lesson_3.Lesson5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kz.example.lesson_3.R;

public class Lesson5Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson51);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.Lesson5Activity1, new Lesson5Fragment1()).commit();
        }
    }

    public void onClickOpenProfile(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Lesson5Activity1, new Lesson5ProfileFragment())
                .commit();
    }

    public void onClickSaveProfile(View view) {
        EditText editText = findViewById(R.id.Lesson5Name);
        TextView textView = findViewById(R.id.textViewLesson5Name);

        SharedPreferences sharedPreferences = getSharedPreferences("Prefer", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Username", editText.getText().toString());
        editor.apply();

        textView.setText("Welcome " + editText.getText());

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.Lesson5Activity1, new Lesson5Fragment1())
//                .commit();
    }

}