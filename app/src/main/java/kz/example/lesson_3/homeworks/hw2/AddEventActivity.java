package kz.example.lesson_3.homeworks.hw2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import kz.example.lesson_3.R;

public class AddEventActivity extends AppCompatActivity {

    private EditText _etTitle, _etDescription, _etStartTime, _etEndTime;
    private CheckBox _cbAllDay;
    private Button _btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        init();

        _cbAllDay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                _etStartTime.setEnabled(false);
                _etEndTime.setEnabled(false);
            } else {
                _etStartTime.setEnabled(true);
                _etEndTime.setEnabled(true);
            }
        });

        _etStartTime.setOnClickListener(v -> showTimePickerDialog(_etStartTime));
        _etEndTime.setOnClickListener(v -> showTimePickerDialog(_etEndTime));

        _btnSave.setOnClickListener(v -> saveEvent());
    }

    private void init() {
        _etTitle = findViewById(R.id.etTitle);
        _etDescription = findViewById(R.id.etDescription);
        _cbAllDay = findViewById(R.id.cbAllDay);
        _etStartTime = findViewById(R.id.etStartTime);
        _etEndTime = findViewById(R.id.etEndTime);
        _btnSave = findViewById(R.id.btnSave);
    }

    private void showTimePickerDialog(EditText editText) {
        TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            editText.setText(String.format("%02d:%02d", hourOfDay, minute));
        }, 12, 0, true);
        timePicker.show();
    }

    private void saveEvent() {
        String title = _etTitle.getText().toString();
        String description = _etDescription.getText().toString();
        boolean isAllDay = _cbAllDay.isChecked();
        long startTime = isAllDay ? 0 : getTimeInMillis(_etStartTime.getText().toString());
        long endTime = isAllDay ? 0 : getTimeInMillis(_etEndTime.getText().toString());

        Event newEvent = new Event(1, title, description, startTime, endTime, isAllDay);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("newEvent", newEvent);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    private long getTimeInMillis(String timeString) {
        return System.currentTimeMillis();
    }
}
