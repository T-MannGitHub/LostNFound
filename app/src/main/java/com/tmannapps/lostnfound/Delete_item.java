package com.tmannapps.lostnfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.contentpager.content.Query;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tmannapps.lostnfound.data.DatabaseHelper;
import com.tmannapps.lostnfound.databinding.ActivityDeleteItemBinding;
import com.tmannapps.lostnfound.util.Util;

public class Delete_item extends AppCompatActivity {
    ActivityDeleteItemBinding deleteItemBinding;
    DatabaseHelper dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteItemBinding = ActivityDeleteItemBinding.inflate(getLayoutInflater());
        View view = deleteItemBinding.getRoot();
        setContentView(deleteItemBinding.getRoot());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        dbH = new DatabaseHelper(Delete_item.this, Util.DATABASE_NAME, null, Util.DATABASE_VERSION );
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT " +  Util.DESCRIPTION + " FROM " +Util.TABLE_NAME+" WHERE "+ Util.USER_ID + "= ?", new String[] { String.valueOf(id) });
        cursor1.moveToFirst();
        @SuppressLint("Range") String description = cursor1.getString(cursor1.getColumnIndex("description"));
        Cursor cursor2 = db.rawQuery("SELECT " + Util.L_OR_F  + " FROM " +Util.TABLE_NAME+" WHERE " + Util.USER_ID + "= ?", new String[] { String.valueOf(id) });
        cursor2.moveToFirst();
        @SuppressLint("Range") String LorF = cursor2.getString(cursor2.getColumnIndex("LorF"));
        Cursor cursor3 = db.rawQuery("SELECT " + Util.DATE  + " FROM " +Util.TABLE_NAME+" WHERE " + Util.USER_ID + "= ?", new String[] { String.valueOf(id) });
        cursor3.moveToFirst();
        @SuppressLint("Range") String date = cursor3.getString(cursor3.getColumnIndex("date"));
        Cursor cursor4 = db.rawQuery("SELECT " + Util.LOCATION + " FROM " +Util.TABLE_NAME+" WHERE " + Util.USER_ID + "= ?", new String[] { String.valueOf(id) });
        cursor4.moveToFirst();
        @SuppressLint("Range") String location = cursor4.getString(cursor4.getColumnIndex("location"));

        Cursor cursor = db.query(Util.TABLE_NAME,
                null,null,null,null,null,null);

        while(cursor.moveToNext()){
            int indexDesc = cursor.getColumnIndex("description");
            int indexUserID = cursor.getColumnIndex("user_id");
            int indexLorF = cursor.getColumnIndex("LorF");
            int indexLoc = cursor.getColumnIndex("location");
            int indexDate = cursor.getColumnIndex("date");
            String valueDesc = cursor.getString(indexDesc);
            int valueID = cursor.getInt(indexUserID);
            String valueLorF = cursor.getString(indexLorF);
            String valueLocation = cursor.getString(indexLoc);
            String valueDate = cursor.getString(indexDate);

            deleteItemBinding.textViewItemFound.setText(LorF +" "+ description);
            deleteItemBinding.textViewDateReported.setText(date);
            deleteItemBinding.textViewLocationReported.setText(location);

          deleteItemBinding.buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Cursor cursor = db.rawQuery("DELETE FROM " +Util.TABLE_NAME+" WHERE "+Util.USER_ID +" = ?", new String[] { String.valueOf(id) });
                 cursor.moveToFirst();
                 Intent intent = new Intent(Delete_item.this, AllItems.class);
                 startActivity(intent);
             }
         });

          deleteItemBinding.textViewToMain.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent1 = new Intent(Delete_item.this, MainActivity.class);
                  startActivity(intent1);
              }
          });
    }
}}