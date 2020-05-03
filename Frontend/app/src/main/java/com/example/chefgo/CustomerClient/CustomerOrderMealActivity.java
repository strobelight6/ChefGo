package com.example.chefgo.CustomerClient;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.OrderMealRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
/**
 * @author SB_3
 *
 */

public class CustomerOrderMealActivity extends AppCompatActivity {

    private EditText inputDish, inputPrice;
    private UsersDomain user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_meal);
        user = getIntent().getParcelableExtra("User");

        inputDish = findViewById(R.id.inputAllergy);
        inputPrice = findViewById(R.id.inputPrice);

        Button confirmButton;
        confirmButton = findViewById(R.id.confirmAllergy);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v){
                String meal = inputDish.getText().toString();
                String price = inputPrice.getText().toString();
                Toast.makeText(CustomerOrderMealActivity.this, "Order confirmed.", Toast.LENGTH_LONG).show();
                OrderMealRequests.postOrder(getApplicationContext(), meal, price, user);
                inputDish.getText().clear();
                inputPrice.getText().clear();
            }
        });
    }

}
