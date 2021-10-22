package com.example.tpimagetigran;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load(final View view) {
        final Intent loadImage = new Intent(Intent.ACTION_GET_CONTENT);
        loadImage.setType("image/*");
        startActivityForResult(Intent.createChooser(loadImage, "Choose an image"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            final Uri imageUri = Objects.requireNonNull(data)
                    .getData();

            final ImageView imageView = findViewById(R.id.imageView);
            final TextView textView = findViewById(R.id.textView);

            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable = true;
                final InputStream inputStream = getContentResolver()
                        .openInputStream(imageUri);

                imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream, null, options));
                textView.setText(imageUri.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}