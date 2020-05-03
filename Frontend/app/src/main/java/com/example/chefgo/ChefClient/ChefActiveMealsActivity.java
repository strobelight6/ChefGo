package com.example.chefgo.ChefClient;
/**
 * @author SB_3
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.VolleyCallBack;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class ChefActiveMealsActivity extends AppCompatActivity {

    private TextView title;
    private Button refreshButton;
    private UsersDomain user;
    private ListView listView;

    private String jsonResponse, URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/active";
    private String allergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_active_meals);
        user = getIntent().getParcelableExtra("User");

        title = findViewById(R.id.title);
        listView = findViewById(R.id.listView);

        getActiveMeals();
        refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActiveMeals();
            }
        });
    }

    private void getActiveMeals(){

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            final ArrayList<String> arrayList = new ArrayList<>();
                            
                            for (int i = 0; i < response.length(); i++) {

                                final JSONObject order = (JSONObject) response.get(i);
                                JSONObject customer = order.getJSONObject("customer");

                                getUserAllergies(customer.getString("username"), new VolleyCallBack() {
                                    @Override
                                    public void onSuccess() {
                                        jsonResponse = "";

                                        String oid = "", price = "", dish = "", customerName = "";
                                        try {
                                            oid = order.getString("oid");
                                            price = order.getString("price");
                                            dish = order.getString("dish");
                                            customerName = order.getJSONObject("customer").getString("name");
                                        } catch (JSONException e){
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        jsonResponse += (oid + "\n");
                                        jsonResponse += ("Dish: " + dish + "\n");
                                        jsonResponse += ("Price: " + price + "\n");
                                        jsonResponse += ("Customer name: " + customerName + "\n");

                                        if (allergy != null) {
                                            jsonResponse += ("Allergies: " + allergy);
                                        }
                                        else {
                                            jsonResponse += ("Allergies: none");
                                        }
                                        arrayList.add(jsonResponse);

                                        ArrayAdapter arrayAdapter = new ArrayAdapter(ChefActiveMealsActivity.this, android.R.layout.simple_list_item_1, arrayList);
                                        listView.setAdapter(arrayAdapter);

                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                //launch new intent to accept or decline meal request
                                                Intent i = new Intent(ChefActiveMealsActivity.this, ChefHandleMealActivity.class);
                                                i.putExtra("User", user);
                                                i.putExtra("JSON_RESPONSE", arrayList.get(position));
                                                try {
                                                    i.putExtra("JSON_OBJECT", response.get(position).toString());
                                                } catch(JSONException e){
                                                    e.printStackTrace();
                                                    Toast.makeText(getApplicationContext(),
                                                            "Error: " + e.getMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                                startActivity(i);
                                            }
                                        });
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    private void getUserAllergies(String uid, final VolleyCallBack callback){

        String allergyURL = "http://coms-309-sb-3.misc.iastate.edu:8080/allergies/" + uid;

        JsonArrayRequest req = new JsonArrayRequest(allergyURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            allergy = "";
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject allergyObject = (JSONObject) response.get(i);
                                allergy += (allergyObject.getString("allergy") + " ");
                            }
                            callback.onSuccess();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

}
