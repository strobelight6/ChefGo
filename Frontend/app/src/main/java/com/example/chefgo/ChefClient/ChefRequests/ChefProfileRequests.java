package com.example.chefgo.ChefClient.ChefRequests;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.ChefClient.ChefProfile;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class ChefProfileRequests {

    public static void makeReviewsJSONArrayReq(String REVIEWS_URL, final Context ctx, final ArrayList<String> reviewList,final ListView chefReview) {


        JsonArrayRequest req = new JsonArrayRequest(REVIEWS_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            ArrayList<String> arrayList = new ArrayList<>();
                            String jsonResponse;
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject review = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String reviewer = review.getString("reviewer");
                                String rating = review.getString("rating");
                                String description = review.getString("description");
                                String date = review.getString("date");

                                jsonResponse += ("Reviewer: " + reviewer + "\n");
                                jsonResponse += ("Rating: " + rating + "\n");
                                jsonResponse += ("Chef: " + description + "\n");
                                jsonResponse += ("Date: " + date + "\n");
                                arrayList.add(jsonResponse);

                            }

                            ArrayAdapter reviewAdapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, arrayList);
                            chefReview.setAdapter(reviewAdapter);
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    public static void makeMenuJSONArrayReq(String MENU_URL, final Context ctx, final ArrayList<String> menuList,final ListView chefMenu) {


        JsonArrayRequest req = new JsonArrayRequest(MENU_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object

                            ArrayList<String> arrayList = new ArrayList<>();
                            String jsonResponse;
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject menu = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String title = menu.getString("title");
                                String appetizers = menu.getString("appetizer");
                                String entree = menu.getString("entree");
                                String desert = menu.getString("dessert");
                                String cost = menu.getString("cost");
                                String desc = menu.getString("description");

                                jsonResponse += ("Title: " + title + "\n");
                                jsonResponse += ("Appetizers: " + appetizers + "\n");
                                jsonResponse += ("Entree: " + entree + "\n");
                                jsonResponse += ("Dessert: " + desert + "\n");
                                jsonResponse += ("Cost: " + cost + "\n");
                                jsonResponse += ("Description: " + desc + "\n");
                                arrayList.add(jsonResponse);

                            }
                             ArrayAdapter menuAdapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, arrayList);
                            chefMenu.setAdapter(menuAdapter);
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

}
