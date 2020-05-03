package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonCircle, buttonSquare, buttonTriangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCircle = (Button) findViewById(R.id.buttonCircle);
        buttonSquare = (Button) findViewById(R.id.buttonSquare);
        buttonTriangle = (Button) findViewById(R.id.buttonTriangle);

        buttonCircle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(i);
            }
        });

        buttonSquare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SquareActivity.class);
                startActivity(i);
            }
        });

        buttonTriangle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TriangleActivity.class);
                startActivity(i);
            }
        });


    }
}
