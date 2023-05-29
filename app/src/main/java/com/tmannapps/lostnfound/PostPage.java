package com.tmannapps.lostnfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tmannapps.lostnfound.data.DatabaseHelper;
import com.tmannapps.lostnfound.databinding.ActivityPostPageBinding;
import com.tmannapps.lostnfound.model.User;
import com.tmannapps.lostnfound.util.Util;

public class PostPage extends AppCompatActivity {
    private ActivityPostPageBinding postPageBinding;
    DatabaseHelper db;
    String lostOrFound = "";
    public static int numItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_post_page);
        postPageBinding = ActivityPostPageBinding.inflate(getLayoutInflater());
        View view = postPageBinding.getRoot();
        setContentView(postPageBinding.getRoot());
        db = new DatabaseHelper(this, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);

        postPageBinding.radioButtonLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    postPageBinding.radioButtonFound.setChecked(false);
                    lostOrFound = "Lost: ";
                }});

        postPageBinding.radioButtonFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPageBinding.radioButtonLost.setChecked(false);
                lostOrFound = "Found: ";
            }});

    postPageBinding.floatingActionButtonSave.setOnClickListener(view2 -> {
        int id = 0;
        String username = postPageBinding.editTextName.getText().toString();
        int phone = postPageBinding.editTextPhone.getInputType();
        String description = postPageBinding.editTextDescription.getText().toString();
        String date = postPageBinding.editTextDate.getText().toString();
        String location = postPageBinding.editTextLocation.getText().toString();
        String LorF = lostOrFound;
        numItems +=1;

        long result = db.insertUser(new User(id,username, phone, description, date, location, LorF));
        if (result > 0)
        {
            Intent intent = new Intent(PostPage.this, AllItems.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(PostPage.this, "Item registration failed. Try again.", Toast.LENGTH_SHORT).show();
        }
        if (lostOrFound == "Lost: ")
        {
            postPageBinding.radioButtonLost.setChecked(false);
        } else
        {
            postPageBinding.radioButtonFound.setChecked(false);
            lostOrFound = "";
        }
    });
    }
}