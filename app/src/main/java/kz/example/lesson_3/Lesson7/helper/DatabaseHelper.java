package kz.example.lesson_3.Lesson7.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    // Названия таблиц
    public static final String TABLE_RECIPE = "recipes";
    public static final String TABLE_INGREDIENT = "ingredients";

    // Общие колонки
    public static final String COLUMN_ID = "_id";

    // Колонки для рецептов
    public static final String COLUMN_RECIPE_NAME = "recipe_name";

    // Колонки для ингредиентов
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_IMAGE_RESOURCE_ID = "image_id";
    public static final String COLUMN_RECIPE_ID = "recipe_id";  // Внешний ключ для связи с таблицей рецептов

    // SQL-запрос для создания таблицы recipes
    private static final String CREATE_RECIPE_TABLE =
            "CREATE TABLE " + TABLE_RECIPE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RECIPE_NAME + " TEXT);";

    // SQL-запрос для создания таблицы ingredients
    private static final String CREATE_INGREDIENT_TABLE =
            "CREATE TABLE " + TABLE_INGREDIENT + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_QUANTITY + " INTEGER, " +
                    COLUMN_UNIT + " TEXT, " +
                    COLUMN_IMAGE_RESOURCE_ID + " INTEGER, " +
                    COLUMN_RECIPE_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COLUMN_RECIPE_ID + ") REFERENCES " + TABLE_RECIPE + "(" + COLUMN_ID + "));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        onCreate(db);
    }
}
