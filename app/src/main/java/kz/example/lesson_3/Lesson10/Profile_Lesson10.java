package kz.example.lesson_3.Lesson10;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.io.File;

import kz.example.lesson_3.R;

public class Profile_Lesson10 extends AppCompatActivity {

    private ImageView imageView;
    private TextView usernameView;
    private TextView ageView;
    private TextView cityView;
    private Button btnDeleteCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_lesson10);

        imageView = findViewById(R.id.image_profile_less10);
        usernameView = findViewById(R.id.username_profile_less10);
        ageView = findViewById(R.id.age_profile_less10);
        cityView = findViewById(R.id.city_profile_less10);
        btnDeleteCache = findViewById(R.id.deleteCacheBtn_profile_less10);

        loadData();

        btnDeleteCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCache();
            }
        });
    }

    private void loadData() {
        SharedPreferences shp = getSharedPreferences("UserData", MODE_PRIVATE);

        String urlImage = shp.getString("urlImage", null);
        String username = shp.getString("username", null);
        String age = shp.getString("age", null);
        String city = shp.getString("city", null);

        if (urlImage != null && username != null && age != null && city != null) {
            Picasso.get().load(urlImage).into(imageView);
            usernameView.setText("Username: " + username);
            ageView.setText("Age: " + age);
            cityView.setText("City: " + city);
        }

    }

    private void deleteCache() {
        SharedPreferences shp = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.clear();
        editor.apply();

        finishAffinity();
    }
}