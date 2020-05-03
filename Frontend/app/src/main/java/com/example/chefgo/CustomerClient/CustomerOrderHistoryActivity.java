package com.example.chefgo.CustomerClient;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.CustomerOrderHistoryRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

/**
 * @author SB_3
 */

public class CustomerOrderHistoryActivity extends AppCompatActivity {

    UsersDomain user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_history);
        ListView listView = findViewById(R.id.listview);
        TextView description = findViewById(R.id.orderHistoryDescription);

        user = getIntent().getParcelableExtra("User");

        final String ORDER_HISTORY_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";
        CustomerOrderHistoryRequests.getJSONArrayRequest(this.getApplicationContext(), ORDER_HISTORY_URL, description, listView, user);
    }

}

