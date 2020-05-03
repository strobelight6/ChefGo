package com.example.profilepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = findViewById(R.id.login);
        Button sign_up = findViewById(R.id.sign_up);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                Toast.makeText(getApplicationContext(), "Login unavailable", Toast.LENGTH_LONG).show();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAccount = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(createAccount);
            }
        });
    }


}
