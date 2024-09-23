package kz.example.lesson_3.Lesson11;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.biometrics.BiometricManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import kz.example.lesson_3.R;

public class MainActivity_Lesson11 extends AppCompatActivity {

    private EditText fileName, data;
    private Button btnSave, btnSelect;
    private Bitmap bitmap;
//    private Button btnRead;
    private TextView result, desc;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lesson11);

        fileName = findViewById(R.id.et_less11_fileName);
        data = findViewById(R.id.etData_less11);
//        btnSave = findViewById(R.id.btnSaveInternal_less11);
//        btnRead = findViewById(R.id.btnReadInternal_less11);
        btnSelect = findViewById(R.id.btnSelectImage_less11);
        btnSave = findViewById(R.id.btnSaveImage_less1);
        result = findViewById(R.id.tvResult);
        desc = findViewById(R.id.tvDesc_less11);
        imageView = findViewById(R.id.imageView_less11);

//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ))

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data = readFileInternal(fileName.getText().toString());
//                result.setText(data);
                openGallery();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                writeFileInternal(fileName.getText().toString(), data.getText().toString());
                try {
                    saveDataImage(fileName.getText().toString(),data.getText().toString(), bitmap);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            displaySavedData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null);
        Uri imageUri = data.getData();
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    private void saveDataImage(String title, String desc, Bitmap image) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("desc", desc);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        jsonObject.put("image", encodedImage);

        File extFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "savedData.json");
        FileOutputStream fos = new FileOutputStream(extFile);
        fos.write(jsonObject.toString().getBytes());
        Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show();
    }

    private void displaySavedData() throws IOException {
        File extFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "savedData.json");
        if(extFile.exists()){
            FileInputStream fileInputStream = new FileInputStream(extFile);
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();

            String jsonStr = new String(buffer, "UTF-8");
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(jsonStr);
            }catch (JSONException e){
                throw new RuntimeException(e);
            }

            try {
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("desc");
                String image = jsonObject.getString("image");

                result.setText(title);
                desc.setText(description);
                byte[] decoded = Base64.decode(image, Base64.DEFAULT);
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
                imageView.setImageBitmap(bitmap1);

            }catch (JSONException e){
                throw new RuntimeException();
            }
        }
    }

    private  void writeFileInternal(String fileName, String data){
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(data.getBytes());
            Toast.makeText(this, "Image saved to internal storage", Toast.LENGTH_SHORT).show();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String readFileInternal(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  stringBuilder.toString();
    }

}