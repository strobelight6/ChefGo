package com.example.chefgo.ChefClient;
/**
 * @author SB_3
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.Chat.ChatActivity;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.LoginorRegistrationActivity.LoginActivity;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;

public class ChefHandleMealActivity extends AppCompatActivity {

    private UsersDomain user;
    private TextView description;
    private Button acceptButton;
    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/active",itemDescription, oid;
    private String putURL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/updateChef/";
    private JSONObject order;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_handle_meal);
        itemDescription = getIntent().getStringExtra("JSON_RESPONSE");
        final String[] fields = splitJSONResponse(itemDescription);
        oid = fields[0];
        String jsonString = getIntent().getStringExtra("JSON_OBJECT");

        try {
            order = new JSONObject(jsonString);
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        user = getIntent().getParcelableExtra("User");

        description = findViewById(R.id.handleMealText);
        description.setText(itemDescription);

        acceptButton = findViewById(R.id.buttonAccept);
        acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Map<String, String> chefMap = user.toJSON();
                JSONObject chefObject = new JSONObject(chefMap);
                try {
                    order.put("chef", chefObject);
                    postJSONObjectRequest(chefObject);

                } catch(JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                Intent chatIntent = new Intent(ChefHandleMealActivity.this, ChatActivity.class);
                chatIntent.putExtra("User", user);
                chatIntent.putExtra("oid", fields[0]);
                chatIntent.putExtra("customer", fields[3]);
                startActivity(chatIntent);
            }
        });
    }

    private void postJSONObjectRequest(JSONObject chefObject){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, putURL + oid, chefObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ChefHandleMealActivity.this, "Order accepted", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        LoginActivity.ws.send(oid);
    }

    /**
     * split JSON response
     * @param response
     * @return
     */
    public static String[] splitJSONResponse(String response){
        return response.split("\n");
    }
}
