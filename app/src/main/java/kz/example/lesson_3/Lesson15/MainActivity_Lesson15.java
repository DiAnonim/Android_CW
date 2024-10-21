package kz.example.lesson_3.Lesson15;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.example.lesson_3.R;
 
public class MainActivity_Lesson15 extends AppCompatActivity {

    private List<Contact> contactArrayList = new ArrayList<>();;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson15);

        RecyclerView recyclerView = findViewById(R.id.recyclerView21);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
        } else {
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = " - ";
                    String phone = " - ";

                    int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    if (nameIndex >= 0) {
                        name = cursor.getString(nameIndex);
                    }

                    int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    if (phoneIndex >= 0) {
                        phone = cursor.getString(phoneIndex);
                    }

                    contactArrayList.add(new Contact(name, phone));

                }
                cursor.close();
            } else {
                Log.d("Contact", "Контакт не найден.");
            }
        }

        contactAdapter = new ContactAdapter(contactArrayList);
        recyclerView.setAdapter(contactAdapter);
    }
}