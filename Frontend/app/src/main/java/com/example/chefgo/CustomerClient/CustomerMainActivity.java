package com.example.chefgo.CustomerClient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.ViewMealRequests;

public class CustomerMainActivity extends AppCompatActivity {

    private UsersDomain user;

    /**
     * view of the customer to interact with the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getIntent().getParcelableExtra("User");
        Button map = findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerMapIntent = new Intent(CustomerMainActivity.this, CustomerMapsActivity.class);
                customerMapIntent.putExtra("User", user);
                startActivity(customerMapIntent);
            }
        });

        Button customerProfileButton = findViewById(R.id.buttonCustomerProfile);
        customerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerProfileIntent = new Intent(CustomerMainActivity.this, CustomerProfileActivity.class);
                customerProfileIntent.putExtra("User", user);
                startActivity(customerProfileIntent);
            }
        });

        Button customerOrderHistoryButton = findViewById(R.id.buttonCustomerOrderHistory);
        customerOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderHistoryIntent = new Intent(CustomerMainActivity.this, CustomerOrderHistoryActivity.class);
                orderHistoryIntent.putExtra("User", user);
                startActivity(orderHistoryIntent);
            }
        });

        Button customerOrderMeal = findViewById(R.id.buttonCustomerOrderMeal);
        customerOrderMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent orderMealIntent = new Intent(CustomerMainActivity.this, CustomerOrderMealActivity.class);
                orderMealIntent.putExtra("User", user);
                startActivity(orderMealIntent);
            }
        });

        Button activeMealsButton = findViewById(R.id.activeMealsButton);
        activeMealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activeMealsIntent = new Intent(CustomerMainActivity.this, ViewMealRequests.class);
                startActivity(activeMealsIntent);
            }
        });

        Button acceptedMealButton = findViewById(R.id.accpetedMealButton);
        acceptedMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accepted = new Intent(CustomerMainActivity.this, CustomerMealAccepted.class);
                accepted.putExtra("User", user);
                startActivity(accepted);
            }
        });
        Button allergiesButton = findViewById(R.id.allergies);
        allergiesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent allergiesIntent = new Intent(CustomerMainActivity.this, CustomerAllergiesActivity.class);
                allergiesIntent.putExtra("User", user);
                startActivity(allergiesIntent);
            }
        });

    }
}
