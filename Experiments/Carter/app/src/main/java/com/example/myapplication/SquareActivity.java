package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SquareActivity extends AppCompatActivity {

    Button buttonSubmit;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editText = findViewById(R.id.editTextBox);
                textView = findViewById(R.id.displayResults);
                String temp = editText.getText().toString();
                double val = Double.parseDouble(temp);
                val *= val;
                textView.setText("Area: " + Double.toString(val));
            }
        });

    }
}
