package com.example.chefgo.CustomerClient.CustomerRequests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class MapsRequests {

    public static void getJSONArrayRequest(final Context c, final SupportMapFragment map , final OnMapReadyCallback ready, String URL, final ArrayList<UsersDomain> chefs){
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                UsersDomain chef = new UsersDomain();


                                JSONObject person = (JSONObject) response.get(i);
                                chef.setUsername(person.getString("username"));
                                chef.setEmail(person.getString("email"));
                                chef.setName(person.getString("name"));
                                chef.setAddress(person.getString("address"));
                                chef.setState(person.getString("state"));
                                chef.setZip(person.getInt("zip"));
                                chef.setPassword(person.getString("password"));
                                chef.setRating(person.getDouble("rating"));
                                chef.setUserType(person.getInt("userType"));



                                chefs.add(chef);

                            }
                            map.getMapAsync(ready);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(c,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(c,
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

}
