package kz.example.lesson_3.Lesson10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.R;

public class MainActivity_Lesson10 extends AppCompatActivity {

    private EditText url;
    private EditText username;
    private EditText age;
    private EditText city;
    private Button saveButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson10);

        url = findViewById(R.id.et_less10_urlImage);
        username = findViewById(R.id.et_less10_username);
        age = findViewById(R.id.et_less10_age);
        city = findViewById(R.id.et_less10_city);
        saveButton = findViewById(R.id.btn_less10_saveData);
        intent = new Intent(this, Profile_Lesson10.class);

        loading();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                startActivity(intent);
            }
        });

    }

    private void saveData() {
        String urlVal = url.getText().toString();
        String usernameVal = username.getText().toString();
        String ageVal = age.getText().toString();
        String cityVal = city.getText().toString();

        if (!urlVal.isEmpty() && !usernameVal.isEmpty() && !ageVal.isEmpty() && !cityVal.isEmpty()) {

            SharedPreferences shp = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = shp.edit();

            editor.putString("urlImage", urlVal);
            editor.putString("username", usernameVal);
            editor.putString("age", ageVal);
            editor.putString("city", cityVal);
            editor.apply();

        } else {
            Toast.makeText(this, "ERROR: not all fields are filled in", Toast.LENGTH_SHORT).show();
        }
    }

    private void loading() {
        SharedPreferences shp = getSharedPreferences("UserData", MODE_PRIVATE);

        String urlImage = shp.getString("urlImage", null);
        String username = shp.getString("username", null);
        String age = shp.getString("age", null);
        String city = shp.getString("city", null);

        if(urlImage != null && username != null && age != null && city != null){
            startActivity(intent);
        }

//        SharedPreferences.Editor editor = shp.edit();
//        editor.remove("username");
//        editor.commit();

    }


}