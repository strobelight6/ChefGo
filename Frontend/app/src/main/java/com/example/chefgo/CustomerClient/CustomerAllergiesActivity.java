package com.example.chefgo.CustomerClient;
/**
 * @author SB_3
 *
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.AllergiesRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

public class CustomerAllergiesActivity extends AppCompatActivity {

    private UsersDomain user;
    private EditText allergy;
    private String ALLERGIES_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/allergies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);
        allergy = findViewById(R.id.inputAllergy);
        Button confirm = findViewById(R.id.confirmAllergy);

        user = new UsersDomain();
        user = getIntent().getParcelableExtra("User");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllergiesRequests.postJSONObjectRequest(getApplicationContext(), ALLERGIES_URL, user, allergy);
            }
        });
    }

}
