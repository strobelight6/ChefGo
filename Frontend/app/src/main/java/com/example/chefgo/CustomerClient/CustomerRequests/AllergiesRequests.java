package com.example.chefgo.CustomerClient.CustomerRequests;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class AllergiesRequests {

    public static void postJSONObjectRequest(final Context c, String URL, UsersDomain user, final EditText allergy){
        JSONObject allergyObject = new JSONObject();

        JSONObject customer = new JSONObject(user.toJSON());
        try {
            allergyObject.put("allergy", allergy.getText().toString());
            allergyObject.put("user", customer);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, URL, allergyObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(c, "Allergy placed successfully", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c, "Allergy placed successfully", Toast.LENGTH_LONG).show();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) ;
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

}
