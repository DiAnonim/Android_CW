package kz.example.lesson_3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivityCalc extends AppCompatActivity {

    EditText number1;
    EditText number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void ClickPlus(View view){
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        int result = Integer.valueOf(number1.getText().toString()) + Integer.valueOf(number2.getText().toString());

        TextView textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setText("Result: " + result);
    }

    public void ClickMinus(View view){
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        int result = Integer.valueOf(number1.getText().toString()) - Integer.valueOf(number2.getText().toString());

        TextView textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setText("Result: " + result);
    }

    public void ClickMult(View view){
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        int result = Integer.valueOf(number1.getText().toString()) * Integer.valueOf(number2.getText().toString());

        TextView textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setText("Result: " + result);
    }

    public void ClickDivision(View view){
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        TextView textViewResult = findViewById(R.id.textViewResult);

        if(Integer.valueOf(number2.getText().toString()) == 0) {
            String result = "Error: It cannot be divided by zero";
            textViewResult.setText("Result: " + result);
        }
        else {
            int result = Integer.valueOf(number1.getText().toString()) + Integer.valueOf(number2.getText().toString());
            textViewResult.setText("Result: " + result);
        }

    }
}