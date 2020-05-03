package com.example.chefgo.CustomerClient.CustomerRequests;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.Adapters.OrderHistoryAdapter;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerOrderHistoryRequests {

    public static void getJSONArrayRequest(final Context ctx, String URL, final TextView description, final ListView listView, final UsersDomain user){
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();

                            for (int i = response.length()-1; i >= 0; i--) {

                                JSONObject order = (JSONObject) response.get(i);
                                String jsonResponse = "";

                                int oid = order.getInt("oid");
                                String price = order.getString("price");
                                String dish = order.getString("dish");
                                String date = order.getString("date");
                                String customerUsername = order.getJSONObject("customer").getString("username");
                                String active = order.getInt("isActive") == 1 ? "Yes" : "No";

                                String chefName;
                                if (order.has("chef") && order.isNull("chef")){
                                    chefName = "TBD";
                                }
                                else {
                                    chefName = order.getJSONObject("chef").getString("name");
                                }

                                if (customerUsername.equals(user.getUsername())) {
                                    jsonResponse += ("Order id: " + oid + "\n");
                                    jsonResponse += ("Dish: " + dish + "\n");
                                    jsonResponse += ("Chef: " + chefName + "\n");
                                    jsonResponse += ("Price: " + price + "\n");
                                    jsonResponse += ("Date: " + date + "\n");
                                    jsonResponse += ("Active: " + active + "\n");
                                    arrayList.add(jsonResponse);
                                }

                            }
                            if (arrayList.isEmpty()){
                                description.setText("You have no order history.");
                            }
                            OrderHistoryAdapter arrayAdapter = new OrderHistoryAdapter(arrayList, ctx, user);
                            listView.setAdapter(arrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ctx,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(ctx,
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
}
