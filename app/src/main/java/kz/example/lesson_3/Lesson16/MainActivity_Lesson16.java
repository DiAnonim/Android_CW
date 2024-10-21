package kz.example.lesson_3.Lesson16;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.provider.Telephony;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import kz.example.lesson_3.R;

public class MainActivity_Lesson16 extends AppCompatActivity {

    private TextView tvCallLog, tvSmsLog;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson16);

        tvCallLog = findViewById(R.id.callLogHistory);
        tvSmsLog = findViewById(R.id.smsLogHistory);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_SMS, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, PERMISSION_REQUEST_CODE);
        } else {
//            loadCallLogs();
//            loadSMSLogs();
//            addEventToCalendar();
        }
    }

    private void loadCallLogs() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder callLogs = new StringBuilder();
            do {
                @SuppressLint("Range")
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                @SuppressLint("Range")
                String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
                @SuppressLint("Range")
                String callDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                @SuppressLint("Range")
                String callDuration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));

                callLogs.append("Номер: ").append(number)
                        .append(", Тип: ").append(type)
                        .append(", Дата: ").append(callDate)
                        .append(", Длительность").append(callDuration).append(" сек. \n\n\n");
            } while (cursor.moveToNext());
            cursor.close();
            tvCallLog.setText(callLogs.toString());

        }
    }

    private void loadSMSLogs() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder smsLogs = new StringBuilder();
            do {
                @SuppressLint("Range")
                String sender = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                @SuppressLint("Range")
                String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));

                smsLogs.append("От: ").append(sender)
                        .append(",\n Сообщение: ").append(body).append("\n\n\n");

            } while (cursor.moveToNext());
            cursor.close();
            tvSmsLog.setText(smsLogs.toString());

        }
    }

//    private void addEventToCalendar() {
//        long calID = 1;
//        long startMillis;
//        long endMillis;
//
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2024, Calendar.OCTOBER, 20, 9, 23);
//        startMillis = beginTime.getTimeInMillis();
//
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2024, Calendar.OCTOBER, 10, 10, 30);
//        endMillis = endTime.getTimeInMillis();
//
//        ContentValues values = new ContentValues();
//        values.put(CalendarContract.Events.CALENDAR_ID, calID);
//        values.put(CalendarContract.Events.TITLE, "ЭКЗАМЕН");
//        values.put(CalendarContract.Events.DESCRIPTION, "Защита нового проекта");
//        values.put(CalendarContract.Events.DTSTART, startMillis);
//        values.put(CalendarContract.Events.DTEND, endMillis);
//        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Almaty");
//
//        Uri uri = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, values);
//
//        long eventID = ContentUris.parseId(uri);
//
//        ContentValues reminderValues  = new ContentValues();
//        reminderValues.put(CalendarContract.Reminders.EVENT_ID, eventID);
//        reminderValues.put(CalendarContract.Reminders.MINUTES, 10);
//        reminderValues.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
//
//        Toast.makeText(this, "Событие добавленно в календарь", Toast.LENGTH_SHORT).show();
//    }

}