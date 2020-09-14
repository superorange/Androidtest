package com.example.android.media;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MediaInsert extends ContentProvider {
   private static  UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI("com.example.content.provider","test",1);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//       if(matcher.match(uri)==1)
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

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

class  MyParcelAble implements Parcelable{
    int age;
    String name;
    protected MyParcelAble(Parcel in) {
        age=in.readInt();

    }
    public static final Creator<MyParcelAble> CREATOR = new Creator<MyParcelAble>() {
        @Override
        public MyParcelAble createFromParcel(Parcel in) {
            return new MyParcelAble(in);
        }

        @Override
        public MyParcelAble[] newArray(int size) {
            return new MyParcelAble[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
