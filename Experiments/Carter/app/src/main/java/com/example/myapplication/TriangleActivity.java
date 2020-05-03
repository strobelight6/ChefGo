package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TriangleActivity extends AppCompatActivity {

    Button buttonSubmit;
    EditText editHeight, editBase;
    TextView textHeight, textBase, displayResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editHeight = findViewById(R.id.editHeight);
                editBase = findViewById(R.id.editBase);
                textHeight = findViewById(R.id.textHeight);
                textBase = findViewById(R.id.textBase);
                displayResults = findViewById(R.id.displayResults);

                double height = Double.parseDouble(editHeight.getText().toString());
                double base = Double.parseDouble(editBase.getText().toString());
                double area = (height * base) / 2;

                displayResults.setText("Area: " + Double.toString(area));
            }
        });
    }
}
