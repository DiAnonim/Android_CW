package kz.example.lesson_3.Lesson12.config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE users (id INTEGER PRIMARY KEY, username TEXT)";
        db.execSQL(createTableQuery);
//        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
//        if(cursor.moveToFirst()){
//            do {
//                int id = cursor.getInt(0);
//                String name = cursor.getString(1);
//            }while (cursor.moveToNext());
//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }


}
