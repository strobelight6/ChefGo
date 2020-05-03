package com.example.chefgo.CustomerClient.CustomerRequests;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerMealAcceptedRequests {

    public static void makeJSONArrayReq(final Context c, final String URL, final TextView mealText){
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response){
                        Log.d(TAG, response.toString());
                        try {
                            String chef;

                            JSONObject order = (JSONObject) response.get(0);
                            chef = order.getString("chef");

                            //Check if there is a chef
                            if(chef == null){
                                mealText.setText("Your meal has not been accepted");
                            }else{//Meal accepted by: chefName
                                JSONObject chefJson = new JSONObject(chef);
                                String chefName = chefJson.getString("name");
                                mealText.setText("Your meal has been accepted! \n\n Chef: " + chefName);
                            }
                        } catch (JSONException e) {
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

}
