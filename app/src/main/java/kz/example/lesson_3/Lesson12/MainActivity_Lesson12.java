package kz.example.lesson_3.Lesson12;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import kz.example.lesson_3.Lesson12.config.DatabaseHelper;
import kz.example.lesson_3.Lesson12.dao.AppDatabase;
import kz.example.lesson_3.Lesson12.dao.User;
import kz.example.lesson_3.Lesson12.dao.UserDao;
import kz.example.lesson_3.R;

public class MainActivity_Lesson12 extends AppCompatActivity {

    private AppDatabase appDatabase;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson12);

        appDatabase = AppDatabase.getDatabase(this);
        userDao = appDatabase.userDao();

        new Thread(() -> {
            User newUser = new User("Pokemon");
            userDao.insertUser(newUser);
            Log.d("Room", "User inserted");

            newUser.setUsername("pikachu");
            userDao.updateUser(newUser);
            Log.d("Room", "User updated");

            List<User> users = userDao.getAllUsers();
            for (User user : users) {
                Log.d("Room", "User: " + user.getUsername());
            }

            userDao.deleteUser(newUser);
            Log.d("Room", "User deleted");
        }).start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}