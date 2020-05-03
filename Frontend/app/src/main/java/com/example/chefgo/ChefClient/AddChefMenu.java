package com.example.chefgo.ChefClient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.ChefClient.ChefRequests.AddChefRequests;
import com.example.chefgo.DomainObjects.MenuDomain;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddChefMenu extends AppCompatActivity {

    private UsersDomain user;

    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/menus";

    private EditText titleText;
    private EditText appsText;
    private EditText entreeText;
    private EditText dessertText;
    private EditText descText;
    private EditText costText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chef_menu);

        //Get the user
        user =  getIntent().getParcelableExtra("User");

        //find views
        titleText = findViewById(R.id.titleText);
        appsText = findViewById(R.id.appsText);
        entreeText = findViewById(R.id.entreeText);
        dessertText = findViewById(R.id.dessertText);
        descText = findViewById(R.id.descText);
        costText = findViewById(R.id.costText);
        submitButton = findViewById(R.id.submitButton);
        final Context ctx = this;
        //Set onClick listeners
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddChefRequests.postMenu(URL, ctx,titleText.getText().toString(),
                        appsText.getText().toString(),
                        entreeText.getText().toString(),
                        dessertText.getText().toString(),
                        descText.getText().toString(),
                        costText.getText().toString(),
                        user);
                Intent chefProfile = new Intent(AddChefMenu.this, ChefProfile.class);
                chefProfile.putExtra("User", user);
                startActivity(chefProfile);
            }
        });

    }



}
