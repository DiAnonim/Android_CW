package kz.example.lesson_3.Lesson7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kz.example.lesson_3.Lesson7.adapter.IngredientAdapter;
import kz.example.lesson_3.Lesson7.helper.DatabaseHelper;
import kz.example.lesson_3.Lesson7.model.Ingredient;
import kz.example.lesson_3.Lesson7.model.Recipe;
import kz.example.lesson_3.R;

public class Lesson7Main extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7_main);
        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView_ing);
    }

    public void onClickSaveInDBRecipe(View view){
        EditText edText_RecipeName = findViewById(R.id.edText_recipe_name);
        EditText edText_ingName = findViewById(R.id.edText_ing_name);
        EditText edQuantity = findViewById(R.id.edQuantity);
        EditText edUnit = findViewById(R.id.edUnit);

        Ingredient ingredient = new Ingredient(
                Integer.parseInt(edQuantity.getText().toString()),
                edText_ingName.getText().toString(),
                edUnit.getText().toString());

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);
        Recipe recipe = new Recipe(edText_RecipeName.getText().toString(), ingredientList);
        long recipeId = SaveInDBRecipe(recipe);
        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, loadRecipe(recipeId));
        listView.setAdapter(ingredientAdapter);
    }

    public long SaveInDBRecipe(Recipe recipe){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues recipeValues = new ContentValues();
        recipeValues.put("recipe_name", recipe.getTitle());
        long recipeId = db.insert(DatabaseHelper.TABLE_RECIPE, null, recipeValues);

        for(Ingredient ingredient: recipe.getIngredientList()){
            ContentValues ingredientValues = new ContentValues();
            ingredientValues.put("recipe_id", recipeId);
            ingredientValues.put("name", ingredient.getName());
            ingredientValues.put("quantity", ingredient.getQuantity());
            ingredientValues.put("unit", ingredient.getUnit());
            db.insert(DatabaseHelper.TABLE_INGREDIENT, null, ingredientValues);
        }
        return recipeId;
    }

    public List<Ingredient> loadRecipe(long recipeId){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_RECIPE, null, "_id = ?", new String[]{String.valueOf(recipeId)}, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            String recipeName = cursor.getString(cursor.getColumnIndexOrThrow("recipe_name"));

            Cursor ingredientsCursor = db.query(DatabaseHelper.TABLE_INGREDIENT, null, "recipe_id = ?", new String[]{String.valueOf(recipeId)}, null, null, null);

            List<Ingredient> ingredientList = new ArrayList<>();

            while (ingredientsCursor.moveToNext()) {
                String name = ingredientsCursor.getString(ingredientsCursor.getColumnIndexOrThrow("name"));
                int quantity = ingredientsCursor.getInt(ingredientsCursor.getColumnIndexOrThrow("quantity"));
                String unit = ingredientsCursor.getString(ingredientsCursor.getColumnIndexOrThrow("unit"));
                int image = ingredientsCursor.getInt(ingredientsCursor.getColumnIndexOrThrow("image_id"));

                ingredientList.add(new Ingredient(quantity, name, unit, image));
            }
            ingredientsCursor.close();

            cursor.close();
            return ingredientList;
        } else {
            cursor.close();
            return null;
        }
    }
}
