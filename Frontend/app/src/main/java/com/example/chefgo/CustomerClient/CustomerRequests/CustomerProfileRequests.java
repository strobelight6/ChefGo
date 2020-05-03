package com.example.chefgo.CustomerClient.CustomerRequests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.app.AppController;

import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerProfileRequests {

    public static void updateCustomerName(String update_cust_url){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, update_cust_url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

}
