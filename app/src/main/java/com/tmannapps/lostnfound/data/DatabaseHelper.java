package com.tmannapps.lostnfound.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tmannapps.lostnfound.model.User;
import com.tmannapps.lostnfound.util.Util;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }
//may need id here todo
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Util.USERNAME + " TEXT," +
                Util.PHONE + " INTEGER," +
                Util.DESCRIPTION + " TEXT," +
                Util.DATE + " DATE," +
                Util.LOCATION + " TEXT," +
                Util.L_OR_F + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        onCreate(db);
    }

    public long insertUser (User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.USERNAME, user.getUsername());
        contentValues.put(Util.PHONE, user.getPhone());
        contentValues.put(Util.DESCRIPTION, user.getDescription());
        contentValues.put(Util.DATE, user.getDate());
        contentValues.put(Util.LOCATION, user.getLocation());
        contentValues.put(Util.L_OR_F, user.getLorF());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }
    /*public boolean fetchUser (String username, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.USER_ID},
                Util.USERNAME + "=? and " + Util.DATE + "=?",
                new String[]{username, date}, null, null, null);
                int numberOfRows = cursor.getCount();
                db.close();

                if (numberOfRows > 0)
                    return true;
                else
                    return false;
    }*/
    }

