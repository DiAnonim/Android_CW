package kz.example.lesson_3.Lesson6;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.R;

public class Lesson6_Activity1 extends AppCompatActivity {

    public int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Default
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson61);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


//        ListView
//        ListView listView = findViewById(R.id.listView);
//        String [] data = {"Test1", "Test2", "Test3", "Test4", "Test5"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//        listView.setAdapter(arrayAdapter);

//        GridView
        GridView gridView = findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this, images);
        gridView.setAdapter(imageAdapter);

    }
}