package com.example.chefgo.AdminClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * @author SB_3
 *
 */

import com.example.chefgo.R;

public class AdminActivity extends AppCompatActivity {
    private Button users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        users = findViewById(R.id.usersButton);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent users = new Intent(AdminActivity.this, UsersActivity.class);

                startActivity(users);
            }
        });

    }
}
