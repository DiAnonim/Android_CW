package kz.example.lesson_3.Lesson4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.R;

public class Lesson4Activity1 extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson4);

        progressBar = findViewById(R.id.progressBar4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickCreate(View view){
        CheckBox genderCheck1 = findViewById(R.id.checkBoxMale);
        CheckBox genderCheck2 = findViewById(R.id.checkBoxFemale);
        if(genderCheck1.isChecked() || genderCheck2.isChecked()) {
            Bundle bundle = new Bundle();

            EditText first = findViewById(R.id.firstName);
            EditText last = findViewById(R.id.lastName);
            bundle.putString("firstName", first.getText().toString());
            bundle.putString("lastName", last.getText().toString());

            String gender = (genderCheck1.isChecked()) ? "Male" : "Female";

            bundle.putString("gender", gender);

            Intent intent = new Intent(Lesson4Activity1.this, Lesson4Activity2.class);
            intent.putExtras(bundle);

            progressBar.setProgress(70);
            progressBar.setVisibility(View.VISIBLE);

            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Lesson4Activity1.this, "Loading...", Toast.LENGTH_SHORT).show();
                }
            }, 4000);

            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Сhoose your gender", Toast.LENGTH_SHORT).show();
        }

    }
}