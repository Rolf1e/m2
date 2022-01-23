package com.example.projettigran.component;

import android.content.Context;
import android.widget.Toast;
import com.example.projettigran.ReadRawFileException;
import com.example.projettigran.component.infra.IOFileOperations;
import com.example.projettigran.component.infra.ResourceHandler;

import java.util.Arrays;

public class PalindromeWriter {

    private final ResourceHandler resourceHandler;
    private final IOFileOperations ioFileOperations;

    public static PalindromeWriter create(final ResourceHandler resourceHandler,
                                          final IOFileOperations ioFileOperations) {
        return new PalindromeWriter(resourceHandler, ioFileOperations);
    }

    private PalindromeWriter(final ResourceHandler resourceHandler,
                             final IOFileOperations ioFileOperations) {
        this.resourceHandler = resourceHandler;
        this.ioFileOperations = ioFileOperations;
    }

    public void loadAndWriteIntoFile(final Context context,
                                     final String to,
                                     final Object... identifiers) {

        Arrays.stream(identifiers)
                .forEach(identifier -> loadAndWriteIntoFile(context, to, identifier));
    }

    private void loadAndWriteIntoFile(final Context context,
                                      final String to,
                                      final Object identifier) {
        try {
            resourceHandler.loadFileFromRawFile((int) identifier)
                    .forEach(sentence -> ioFileOperations.save(context, to, sentence, false));
        } catch (ReadRawFileException e) {
            Toast.makeText(context, "Failed to read file with id: " + identifier, Toast.LENGTH_LONG)
                    .show();
        }
    }

}
