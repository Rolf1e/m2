package com.example.contentprovidertigran.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DictionnaryManager {

    private final Context context;
    private final Uri contentUri;

    public static DictionnaryManager create(final Context context,
                                            final Uri contentUri) {
        return new DictionnaryManager(context, contentUri);
    }

    private DictionnaryManager(final Context context,
                               final Uri contentUri) {
        this.context = context;
        this.contentUri = contentUri;
    }

    public void display(final String[] projection,
                        final String selection,
                        final String[] selectionArgs,
                        final String order,
                        final ListView listView) {

        final Cursor cursor = query(projection, selection, selectionArgs, order);

        final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                context,
                android.R.layout.simple_list_item_checked,
                cursor,
                projection,
                new int[]{
                        android.R.id.text1,
                },
                0
        );
        listView.setAdapter(simpleCursorAdapter);
    }

    public void insert(final ContentValues contentValues) {
        context.getContentResolver()
                .insert(contentUri, contentValues);
        Toast.makeText(context, "Inserted", Toast.LENGTH_LONG)
                .show();
    }


    public void delete(final String where,
                       final String[] whereArgs) {

        final int deleted = context.getContentResolver()
                .delete(contentUri, where, whereArgs);

        Toast.makeText(context, "Deleting " + deleted + " items", Toast.LENGTH_SHORT)
                .show();
    }

    private Cursor query(
            final String[] projection,
            final String selection,
            final String[] selectionArgs,
            final String order) {
        return context.getContentResolver()
                .query(contentUri, projection, selection, selectionArgs, order);
    }


}
