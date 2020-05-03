package com.example.chefgo.CustomerClient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.VolleyCallBack;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.example.chefgo.app.AppController.TAG;

public class CustomerReviewOrder extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText reviewBox;
    private Button submitReview;

    private JSONObject order;
    private int oid;
    private float reviewRating;
    private String add_review_url = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory/review/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review_order);

        String selectedOrder = getIntent().getStringExtra("order");
        oid = getIntent().getIntExtra("oid", oid);
        add_review_url += oid;

        TextView orderDescriptionTitle = findViewById(R.id.orderDescriptionTitle);
        TextView orderDescription = findViewById(R.id.orderDescription);
        reviewBox = findViewById(R.id.reviewBox);
        submitReview = findViewById(R.id.submitReview);
        ratingBar = findViewById(R.id.reviewOrderRB);

        orderDescriptionTitle.setPaintFlags(orderDescriptionTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        orderDescription.setText(selectedOrder);
        createOrDisplayReview();
    }

    private void createOrDisplayReview(){
        setOrder(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                try {
                    //order has already been reviewed
                    if (order.has("review") && !order.isNull("review")) {
                        displayReview();
                    }
                    //order hasn't been review and is no longer active
                    else if (order.has("review") && order.isNull("review") && (order.getInt("isActive") == 0)) {
                        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                                ratingBar.setRating(v);
                                reviewRating = v;
                            }
                        });

                        submitReview.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                createReview();
                                Toast.makeText(getApplicationContext(), "Review submitted.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void displayReview(){
        reviewBox.setEnabled(false);
        reviewBox.setClickable(false);
        ratingBar.setEnabled(false);
        ratingBar.setClickable(false);
        submitReview.setVisibility(View.INVISIBLE);

        try{
            if (order.getJSONObject("review").getString("description").equals("")){
                reviewBox.setText("No description provided");
            }
            else {
                reviewBox.setText(order.getJSONObject("review").getString("description"));
            }

            if (order.getJSONObject("review").isNull("rating")){
                ratingBar.setRating(0);
            }
            else {
                ratingBar.setRating((float) order.getJSONObject("review").getDouble("rating"));
            }
        }
        catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void createReview(){

        JSONObject reviewer = null;
        JSONObject reviewee = null;
        String date = Instant.now().toString();
        String description = reviewBox.getText().toString();

        if(order == null){
            Toast.makeText(getApplicationContext(), "Order is null", Toast.LENGTH_LONG).show();
            return;
        }
        else if (order.has("chef") && order.isNull("chef")){
            Toast.makeText(getApplicationContext(), "Chef is null", Toast.LENGTH_LONG).show();
            return;
        }
        else if (order.has("customer") && order.isNull("customer")){
            Toast.makeText(getApplicationContext(), "Customer is null", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            reviewer = order.getJSONObject("customer");
            reviewee = order.getJSONObject("chef");
        } catch(JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        Map<String, String> reviewMap = new HashMap<>();
        reviewMap.put("rid", "0");
        reviewMap.put("rating", Float.toString(reviewRating));
        reviewMap.put("description", description);
        reviewMap.put("date", date);
        JSONObject review = new JSONObject(reviewMap);
        try {
            review.put("reviewer", reviewer);
            review.put("reviewee", reviewee);
        } catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, add_review_url, review,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Review submitted successfully", Toast.LENGTH_LONG).show();
                        reviewBox.setText("");
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void setOrder(final VolleyCallBack callback){
        final String ORDER_HISTORY_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/orderHistory";

        JsonArrayRequest req = new JsonArrayRequest(ORDER_HISTORY_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            order = null;
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject orderObject = (JSONObject) response.get(i);
                                if (orderObject.getInt( "oid") == oid){
                                    order = orderObject;
                                    callback.onSuccess();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
}
