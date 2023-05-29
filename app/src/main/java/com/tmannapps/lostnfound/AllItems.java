package com.tmannapps.lostnfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tmannapps.lostnfound.data.DatabaseHelper;
import com.tmannapps.lostnfound.databinding.ActivityAllItemsBinding;
import com.tmannapps.lostnfound.model.User;
import com.tmannapps.lostnfound.util.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.RowSetMetaData;

public class AllItems extends AppCompatActivity {

    ActivityAllItemsBinding allItemsBinding;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Descriptions> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allItemsBinding = ActivityAllItemsBinding.inflate(getLayoutInflater());
        View view = allItemsBinding.getRoot();
        setContentView(view);
        recyclerView = allItemsBinding.recyclerView;
        recyclerViewAdapter = new RecyclerViewAdapter(itemsList, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper dbH;
        dbH = new DatabaseHelper(AllItems.this, Util.DATABASE_NAME, null, Util.DATABASE_VERSION );
        SQLiteDatabase db = dbH.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,
                null,null,null,null,null,null);
        while(cursor.moveToNext()){
            int indexDesc = cursor.getColumnIndex("description");
            int indexUserID = cursor.getColumnIndex("user_id");
            int indexLorF = cursor.getColumnIndex("LorF");
            String valueDesc = cursor.getString(indexDesc);//this value variable returns all descriptions.
            int valueID = cursor.getInt(indexUserID);
            String valueLorF = cursor.getString(indexLorF);
            Log.i("Values", "message: " + valueLorF + " " + valueDesc );  //Replace "Found" with which button selected and add to recycler view

            for (int i=0; i <= indexUserID; i ++) {
                Descriptions desc = new Descriptions(i, valueLorF, valueDesc);
                itemsList.add(desc);
            }
            dbH.close();
        }
    }
}