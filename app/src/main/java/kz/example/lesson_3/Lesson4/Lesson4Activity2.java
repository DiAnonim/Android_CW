package kz.example.lesson_3.Lesson4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kz.example.lesson_3.R;

public class Lesson4Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson42);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String first = bundle.getString("firstName");
            String last = bundle.getString("lastName");
            String gender = bundle.getString("gender");

            TextView text = findViewById(R.id.textFirstName);
            text.setText("First Name:" + first);

            text = findViewById(R.id.textLastName);
            text.setText("Last Name:" + last);

            text = findViewById(R.id.textGender);
            text.setText("Gender:" + gender);
        }

    }

    public void toggleButtonClick(View view){
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
            Toast.makeText(this, "You click Toggle Button", Toast.LENGTH_SHORT).show();
    }
}