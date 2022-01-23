package com.example.projettigran.component.infra;

import android.content.Context;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IOFileOperations {

    private static IOFileOperations INSTANCE;

    public static IOFileOperations getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new IOFileOperations();
        }
        return INSTANCE;
    }

    private IOFileOperations() {
    }

    public List<String> read(final Context context,
                             final String fileName) {

        final List<String> content = new ArrayList<>();
        final File file = new File(context.getFilesDir(), fileName);
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (null != (line = reader.readLine())) {
                content.add(Objects.requireNonNull(line));
            }
        } catch (IOException e) {
            Toast.makeText(context, "Failed to read file " + fileName, Toast.LENGTH_LONG)
                    .show();
        } finally {
            Toast.makeText(context, "Read file " + fileName, Toast.LENGTH_LONG)
                    .show();
        }

        return content;
    }

    public void save(final Context context,
                     final String fileName,
                     final String sentence,
                     final boolean toast) {

        final File file = new File(context.getFilesDir(), fileName);

        System.out.println(Arrays.toString(file.list()));

        try (final FileOutputStream outputStream = new FileOutputStream(file, true);
             final OutputStreamWriter writer = new OutputStreamWriter(outputStream)
        ) {
            writer.append(sentence)
                    .append('\n');
            writer.flush();
        } catch (IOException e) {
            Toast.makeText(context, "Failed to append sentence to " + fileName, Toast.LENGTH_LONG)
                    .show();
        } finally {
            if (toast) {
                Toast.makeText(context, "Append sentence to file " + fileName, Toast.LENGTH_LONG)
                        .show();
            }
        }

    }
}
