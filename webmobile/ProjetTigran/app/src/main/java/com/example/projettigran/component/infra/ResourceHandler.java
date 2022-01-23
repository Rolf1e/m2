package com.example.projettigran.component.infra;

import android.content.Context;
import android.content.res.Resources;
import com.example.projettigran.ReadRawFileException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceHandler {

    private final Resources resources;

    public static ResourceHandler from(final Context context) {
        return new ResourceHandler(context.getResources());
    }

    private ResourceHandler(final Resources resources) {
        this.resources = resources;
    }

    public List<String> loadFileFromRawFile(final int identifier) throws ReadRawFileException {
        try (final InputStream inputStream = resources.openRawResource(identifier)) {
            final byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(bytes);
            final String s = outputStream.toString();
            return Arrays.stream(s.split("\n"))
                    .collect(Collectors.toList());
        } catch (IOException ioe) {
            throw new ReadRawFileException(identifier);
        }
    }
}
