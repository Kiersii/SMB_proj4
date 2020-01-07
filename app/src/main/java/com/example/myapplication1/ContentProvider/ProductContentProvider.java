package com.example.myapplication1.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication1.Database.Product;
import com.example.myapplication1.Database.ProductRoomDatabase;

public class ProductContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.myapplication1.ContentProvider";
    public static final String TABLE_NAME = Product.class.getSimpleName();
    public static final Uri URI_PRODUCT = Uri.parse("content://"+ AUTHORITY+"/"+TABLE_NAME);

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if(getContext() !=null) {
            long IdProduct = ContentUris.parseId(uri);
            final Cursor cursor =
                    ProductRoomDatabase.getDatabase(getContext()).productDao().getProductWithCursor((int) IdProduct);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

            return cursor;
        }
        throw new IllegalArgumentException("Failed to query row for uri "+ uri);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "android.cursor.product/" + AUTHORITY+"."+TABLE_NAME;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
      //  if(getContext() != null){
        //    final int id=
          //          ProductRoomDatabase.getDatabase(getContext()).productDao().insertProduct(Product.fromContentValues(values));
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
