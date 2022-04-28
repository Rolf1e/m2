package com.example.tpimagetigran;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_LOAD_IMAGE = 1;
    private Uri imageUri;

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
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.generic_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        final int itemId = item.getItemId();
        final ImageView viewById = findViewById(R.id.imageView);
        if (R.id.item_horizontal == itemId) {
            flipImageHori(viewById);
        } else if (R.id.item_vertical == itemId) {
            flipImageVerti(viewById);
        } else if (R.id.item_invert_colors == itemId) {
            flipColors(findViewById((R.id.imageView)));
        } else if (R.id.item_gris == itemId) {
            flipGray(viewById);
        } else if (R.id.item_rotate == itemId) {
            rotateImage(viewById, 90);
        } else if (R.id.item_anti_rotate == itemId) {
            rotateImage(viewById, -90);
        } else if (R.id.item_restore == itemId) {
            loadImage();
        }
        return super.onOptionsItemSelected(item);
    }

    private void flipColors(final ImageView image) {
        final Bitmap bitmap = getBitmap(image);
        final int width = bitmap.getWidth() - 1;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < bitmap.getHeight() - 1; y++) {
                final int pixel = bitmap.getPixel(x, y);
                final int red = Color.red(pixel);
                final int green = Color.green(pixel);
                final int blue = Color.blue(pixel);
                bitmap.setPixel(x, y, Color.rgb(255 - red, 255 - green, 255 - blue));
            }
        }
    }

    private void rotateImage(final ImageView image,
                             final float angle) {
        image.setRotation(image.getRotation() + angle);
    }

    private void flipGray(final ImageView image) {
        final Bitmap bitmap = getBitmap(image);
        final int width = bitmap.getWidth() - 1;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < bitmap.getHeight() - 1; y++) {
                final int pixel = bitmap.getPixel(x, y);
                final int red = Color.red(pixel);
                final int green = Color.green(pixel);
                final int blue = Color.blue(pixel);
                final int mean = (red + green + blue) / 3;
                bitmap.setPixel(x, y, Color.rgb(mean, mean, mean));
            }
        }
    }

    private void flipImageHori(final ImageView image) {
        final Bitmap bitmap = getBitmap(image);
        final int width = bitmap.getWidth() - 1;
        for (int x = 0; x < width / 2; x++) {
            for (int y = 0; y < bitmap.getHeight() - 1; y++) {
                invert(bitmap, x, y, width - x, y);
            }
        }
    }

    private void flipImageVerti(final ImageView image) {
        final Bitmap bitmap = getBitmap(image);
        final int height = bitmap.getHeight() - 1;

        for (int x = 0; x < bitmap.getWidth() - 1; x++) {
            for (int y = 0; y < height / 2; y++) {
                invert(bitmap, x, y, x, height - y);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            imageUri = Objects.requireNonNull(data)
                    .getData();
            loadImage();
        }

    }

    private void loadImage() {
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

    private void invert(final Bitmap bitmap,
                        final int x,
                        final int y,
                        final int x2,
                        final int y2) {

        final int pixel = bitmap.getPixel(x, y);
        final int pixelOppoiste = bitmap.getPixel(x2, y2);
        bitmap.setPixel(x, y, pixelOppoiste);
        bitmap.setPixel(x2, y2, pixel);
    }

    private static Bitmap getBitmap(final ImageView image) {
        return ((BitmapDrawable) image.getDrawable()).getBitmap();
    }
}