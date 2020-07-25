package me.aflak.bluetoothterminal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class Capture extends AppCompatActivity {

    private Button capture;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private String imageDataBase64;
//    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture);

//        pos = getIntent().getExtras().getInt("pos");

        if (savedInstanceState != null)
        {
            imageDataBase64 = savedInstanceState.getString("base64");
//            pos = savedInstanceState.getInt("pos");
        }

        capture = (Button) findViewById(R.id.capture);

        capture.setOnClickListener((View v) -> dispatchTakePictureIntent());
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

//        imageDataBase64 = "Hamedsasvavevv";
//        OpenChatActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String imageString = toBase64(Objects.requireNonNull(imageBitmap));
            imageDataBase64 = imageString;

            OpenSelectActivity();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString("base64", imageDataBase64);
//        outState.putInt("pos", pos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState)
    {
        if (outState != null)
        {
            imageDataBase64 = outState.getString("base64");
//            pos = outState.getInt("pos");
        }
    }

    private void OpenSelectActivity() {
        Intent intent = new Intent(Capture.this, Select.class);
        intent.putExtra("base64", imageDataBase64);
//        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public String toBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
