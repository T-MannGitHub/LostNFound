package com.tmannapps.lostnfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tmannapps.lostnfound.databinding.ActivityDeleteItemBinding;

public class Delete_item extends AppCompatActivity {
    ActivityDeleteItemBinding deleteItemBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteItemBinding = ActivityDeleteItemBinding.inflate(getLayoutInflater());
        View view = deleteItemBinding.getRoot();
        setContentView(deleteItemBinding.getRoot());

          deleteItemBinding.buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Toast.makeText(Delete_item.this, "Delete this item.", Toast.LENGTH_SHORT).show();
                // Delete the item from the db for user_id relating to the item
                 //PostPage.numItems -=1;
             }
         });


    }
}