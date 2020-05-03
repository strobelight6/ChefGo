package com.example.chefgo.CustomerClient;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.CustomerMealAcceptedRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

import org.w3c.dom.Text;

/**
 * @author SB_3
 *
 */

public class CustomerMealAccepted extends AppCompatActivity {

    private String recent_order_url = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/recent/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_meal_accepted);
        TextView mealText = findViewById(R.id.mealBox);

        UsersDomain user = getIntent().getParcelableExtra("User");
        recent_order_url += user.getUsername();
        CustomerMealAcceptedRequests.makeJSONArrayReq(getApplicationContext(), recent_order_url, mealText);
    }

}
