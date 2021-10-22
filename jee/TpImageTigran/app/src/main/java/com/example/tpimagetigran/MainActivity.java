package com.example.tpimagetigran;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    public void flipImage(final View view) {
        final int id = view.getId();
        if (R.id.button_flip_hori == id) {
            flipImageHori(findViewById(R.id.imageView));
        } else if (R.id.button_flip_verti == id) {
            flipImageVerti(findViewById(R.id.imageView));
        }
    }

    private void flipImageHori(final ImageView image) {
        final Bitmap bitmap = getBitmap(image);

        final int width = bitmap.getWidth() - 1;
        for (int x = 0; x < width / 2; x++) {
            for (int y = 0; y < bitmap.getHeight() - 1; y++) {
                final int pixel = bitmap.getPixel(x, y);
                final int pixelOpposite = bitmap.getPixel(width - x, y);
                bitmap.setPixel(x, y, pixelOpposite);
                bitmap.setPixel(width - x, y, pixel);
            }
        }
    }

    private void flipImageVerti(final View viewById) {


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

    private static Bitmap getBitmap(final ImageView image) {
        return ((BitmapDrawable) image.getDrawable()).getBitmap();
    }
}