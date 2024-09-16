package kz.example.lesson_3.Lesson6_ClassWork;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ListView listView = findViewById(R.id.listView1);
        Product [] data = {new Product("product1", "product1Desc", 2135),
                new Product("product2", "product2Desc", 1235),
                new Product("product3", "product3Desc", 5648),};
        ArrayAdapter<Product> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(arrayAdapter);
    }
}