package com.tmannapps.lostnfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tmannapps.lostnfound.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Button newAd, allLostItems;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonNewAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostPage.class);
                startActivity(intent);
            }
        });
        binding.buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AllItems.class);
                startActivity(intent);
            }
        });
    }
}