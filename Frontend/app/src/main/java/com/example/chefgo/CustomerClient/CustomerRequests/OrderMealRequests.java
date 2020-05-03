package com.example.chefgo.CustomerClient.CustomerRequests;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.CustomerClient.CustomerOrderMealActivity;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;

public class OrderMealRequests {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void postOrder(final Context c, String meal, String price, final UsersDomain user){
        final String ORDER_HISTORY_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";
        String date = Instant.now().toString();

        Map<String, String> customerMap = user.toJSON();
        JSONObject customerObject = new JSONObject(customerMap);

        Map<String, String> orderMap = new HashMap<>();
        orderMap.put("isActive", "1");
        orderMap.put("oid", Integer.toString(Integer.MAX_VALUE));
        orderMap.put("price", price);
        orderMap.put("dish", meal);
        orderMap.put("chef", null);
        orderMap.put("customer", null);
        orderMap.put("date", date);
        orderMap.put("review", null);
        JSONObject orderObject = new JSONObject(orderMap);
        try {
            orderObject.put("customer", customerObject);
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(c,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, ORDER_HISTORY_URL, orderObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(c, "Order placed successfully", Toast.LENGTH_LONG).show();
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
