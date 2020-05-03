package com.example.joe_test_application;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.*;

public class Second_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView place = findViewById(R.id.placeDisplay);
        place.setText(choosePlace());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView place = findViewById(R.id.placeDisplay);
        place.setText(choosePlace());
    }

    public String choosePlace(){
        String ret = "";
        int num = new Random().nextInt(57);
        if(num % 2 == 0){
            ret = "Home";
        }else if (num % 3 == 0){
            ret = "New York City";
        }else {
            ret = "School";
        }
        return ret;
    }

}
