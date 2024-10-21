package kz.example.lesson_3.homeworks.hw2;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kz.example.lesson_3.R;

public class MainActivity_hw2 extends AppCompatActivity {

    private CalendarView _calendarView;
    private RecyclerView _rwEvent;
    private EventAdapter _eventAdapter;
    private List<Event> eventList = new ArrayList<>();

    private FloatingActionButton _addEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw2);

        init();

        _calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            loadEvents(selectedDate.getTimeInMillis());
        });

        _addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity_hw2.this, AddEventActivity.class), 1);
            }
        });
    }

    private void init(){
        _calendarView = findViewById(R.id.calView);
        _rwEvent = findViewById(R.id.eventRW);
        _addEvent = findViewById(R.id.fab);

        _eventAdapter = new EventAdapter(eventList);
        _rwEvent.setLayoutManager(new LinearLayoutManager(this));
        _rwEvent.setAdapter(_eventAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                Event newEvent = (Event) data.getSerializableExtra("newEvent");

                eventList.add(newEvent);
                _eventAdapter.notifyDataSetChanged();
            }
        }
    }


    private void loadEvents(long selectedDateMillis) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTimeInMillis(selectedDateMillis);
        beginTime.set(Calendar.HOUR_OF_DAY, 0);
        beginTime.set(Calendar.MINUTE, 0);
        long startMillis = beginTime.getTimeInMillis();

        Calendar endTime = Calendar.getInstance();
        endTime.setTimeInMillis(selectedDateMillis);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        long endMillis = endTime.getTimeInMillis();

        eventList.clear();

        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Events.CONTENT_URI;

        String[] projection = {
                CalendarContract.Events.CALENDAR_ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND,
                CalendarContract.Events.ALL_DAY,
                CalendarContract.Events.EVENT_COLOR
        };

        String selection = "((" + CalendarContract.Events.DTSTART + " >= ?) AND (" +
                CalendarContract.Events.DTSTART + " <= ?))";
        String[] selectionArgs = new String[]{String.valueOf(startMillis), String.valueOf(endMillis)};

        Cursor cursor = cr.query(uri, projection, selection, selectionArgs, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                long calendarId = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events.CALENDAR_ID));
                @SuppressLint("Range")
                String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
                @SuppressLint("Range")
                String description = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
                @SuppressLint("Range")
                long dtStart = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events.DTSTART));
                @SuppressLint("Range")
                long dtEnd = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events.DTEND));
                @SuppressLint("Range")
                boolean allDay = cursor.getInt(cursor.getColumnIndex(CalendarContract.Events.ALL_DAY)) > 0;

                Event event = new Event(calendarId, title, description, dtStart, dtEnd, allDay);
                eventList.add(event);
            }
            cursor.close();
        }

        _eventAdapter.notifyDataSetChanged();
    }
}
