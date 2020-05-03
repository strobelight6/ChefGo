package com.example.chefgo.AdminClient.AdminRequests;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.Adapters.UsersAdapter;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;

public class UsersRequests {
    public static void getJSONArrayRequest(final Context ctx, String URL, ListView listView){

        JsonArrayRequest req = new JsonArrayRequest(URL,
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

                                JSONObject order = (JSONObject) response.get(i);
                                jsonResponse = "";

                                String username = order.getString("username");

                                String userType = order.getString("userType");
                                String user = "";
                                if(userType.equals("1")){
                                    user = "Customer";
                                }
                                else if(userType.equals("0")){
                                    user = "Administrator";
                                }
                                else{
                                    user = "Chef";
                                }
                                String rating = order.getString("rating");

                                jsonResponse += ("Username: " + username + "\n");

                                jsonResponse += ("userType: " + user + "\n");
                                jsonResponse += ("rating: " + rating + "\n");

                                arrayList.add(jsonResponse);
                            }
                            UsersAdapter adapter = new UsersAdapter(arrayList, ctx);


                          //  listView.setAdapter(adapter);

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
