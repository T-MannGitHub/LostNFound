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
import android.widget.TextView;
import android.widget.Toast;

import com.tmannapps.lostnfound.data.DatabaseHelper;
import com.tmannapps.lostnfound.databinding.ActivityAllItemsBinding;
import com.tmannapps.lostnfound.util.Util;

import java.util.ArrayList;
import java.util.List;

public class AllItems extends AppCompatActivity implements RecyclerViewAdapter.OnRowClickListener{

    ActivityAllItemsBinding allItemsBinding;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Descriptions> itemsList = new ArrayList<>();
    DatabaseHelper dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allItemsBinding = ActivityAllItemsBinding.inflate(getLayoutInflater());
        View view = allItemsBinding.getRoot();
        setContentView(view);
        recyclerView = allItemsBinding.recyclerView;
        recyclerViewAdapter = new RecyclerViewAdapter(itemsList, this, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

            for (int i=0; i <= indexUserID; i ++) {
                Descriptions desc = new Descriptions(valueID, valueLorF, valueDesc);
                itemsList.add(desc);
            }
            dbH.close();
        }
        allItemsBinding.textViewLostNFoundHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllItems.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItemClick(int i) {
       switch (i) {
            case 0:
                //get the info from the index of the item in the list equal to position and then the ID of that item, send to delete page, and retrieve based on ID from database
                Intent intentID0= new Intent(AllItems.this, Delete_item.class);
                intentID0.putExtra("id",  itemsList.get(0).getId());
                startActivity(intentID0);
                break;

            case 1:
                //Toast.makeText(this, "Clicked on item 2", Toast.LENGTH_SHORT).show();
                Intent intentID1= new Intent(AllItems.this, Delete_item.class);
                intentID1.putExtra("id",  itemsList.get(1).getId());
                startActivity(intentID1);
                break;

            case 2:
                Intent intentID2= new Intent(AllItems.this, Delete_item.class);
                intentID2.putExtra("id",  itemsList.get(2).getId());
                startActivity(intentID2);
                break;

            case 3:
                Intent intentID3= new Intent(AllItems.this, Delete_item.class);
                intentID3.putExtra("id",  itemsList.get(3).getId());
                startActivity(intentID3);
                break;
            case 4:
                Intent intentID4= new Intent(AllItems.this, Delete_item.class);
                intentID4.putExtra("id",  itemsList.get(4).getId());
                startActivity(intentID4);
                break;
            case 5:
                Intent intentID5= new Intent(AllItems.this, Delete_item.class);
                intentID5.putExtra("id",  itemsList.get(5).getId());
                startActivity(intentID5);
                break;
            case 6:
                Intent intentID6= new Intent(AllItems.this, Delete_item.class);
                intentID6.putExtra("id",  itemsList.get(6).getId());
                startActivity(intentID6);
                break;
            case 7:
                Intent intentID7= new Intent(AllItems.this, Delete_item.class);
                intentID7.putExtra("id",  itemsList.get(7).getId());
                startActivity(intentID7);
                break;
            case 8:
                Intent intentID8= new Intent(AllItems.this, Delete_item.class);
                intentID8.putExtra("id",  itemsList.get(8).getId());
                startActivity(intentID8);
                break;

                default:
                Toast.makeText(this, "You clicked on an item", Toast.LENGTH_SHORT).show();
        }
    }
}