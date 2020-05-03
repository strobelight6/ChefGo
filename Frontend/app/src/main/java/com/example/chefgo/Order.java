package com.example.chefgo;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class Order {

    private int oid, isActive;
    private double price;
    private String dish, date;
    private JSONObject order;

    /*
    public Order(int oid){
        String order_url = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/";
        order_url += oid;

        JsonArrayRequest req = new JsonArrayRequest(order_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response){
                        Log.d(TAG, response.toString());
                        try {
                            order = response.getJSONObject(0);

                        }catch (JSONException e) {
                            System.out.println(e.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
    */
}
