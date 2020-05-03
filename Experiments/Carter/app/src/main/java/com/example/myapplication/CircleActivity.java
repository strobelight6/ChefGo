package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CircleActivity extends AppCompatActivity {

    Button buttonSubmit;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editText = findViewById(R.id.editTextBox);
                textView = findViewById(R.id.displayResults);
                String temp = editText.getText().toString();
                double val = Double.parseDouble(temp);
                val = val * val * Math.PI;
                textView.setText("Area: " + Double.toString(val));
            }
        });
    }
}
