package com.example.chefgo.ChefClient.ChefRequests;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.MenuDomain;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class AddChefRequests {
    public static void postMenu(String URL,final Context ctx, final String title,
                                final String apps,
                                final String entree,
                                final String dessert,
                                final String desc,
                                final String cost,
                                final UsersDomain theUser) {

        if (title.equals("")) {
            Toast.makeText(ctx, "Enter a title bitch.", Toast.LENGTH_SHORT).show();
            return;
        } else if (apps.equals("")) {
            Toast.makeText(ctx, "Enter an app. N/A for none.", Toast.LENGTH_SHORT).show();
            return;
        } else if (entree.equals("")) {
            Toast.makeText(ctx, "Enter an entree. N/A for none.", Toast.LENGTH_SHORT).show();
            return;
        } else if (dessert.equals("")) {
            Toast.makeText(ctx, "Enter a dessert. N/A for none.", Toast.LENGTH_SHORT).show();
            return;
        } else if (desc.equals("")) {
            Toast.makeText(ctx, "Enter a description.", Toast.LENGTH_SHORT).show();
            return;
        } else if (cost.equals("")) {
            Toast.makeText(ctx, "Enter a price.", Toast.LENGTH_SHORT).show();
            return;
        }

        MenuDomain menu = new MenuDomain(title,apps,entree,dessert,Double.valueOf(cost),desc, theUser);

        //Construct the user object for the menu object
        Map<String, String> userMap =theUser.toJSON();

        JSONObject userObject = new JSONObject(userMap);

        //construct the menu object to be posted
        Map<String, String> menuMap = menu.toJSON();
        JSONObject menuObject = new JSONObject(menuMap);

        try {
            menuObject.put("chef", userObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(ctx,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, menuObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                Toast.makeText(ctx, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonRequest);
    }



}
