package com.example.chefgo.ChefClient;
/**
 * @author SB_3
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chefgo.ChefClient.ChefRequests.ChefProfileRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.chefgo.app.AppController.TAG;
import static java.lang.Thread.sleep;

public class ChefProfile extends AppCompatActivity {
    private static ListView chefMenu;
    private static ListView chefReviews;
    private RatingBar chefRating;
    private TextView chefName;
    private Button addMealButton;

    private static ArrayList<String> reviewList;
    private static ArrayList<String> menuList;
    private UsersDomain user;

    private String REVIEWS_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/reviewee/";
    private String MENU_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/menus/chef/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_profile);

        //Find necessary views
        chefMenu = findViewById(R.id.menuList);
        chefReviews = findViewById(R.id.chefReviewList);
        chefRating = findViewById(R.id.chefRating);
        chefName = findViewById(R.id.chefNameText);
        addMealButton = findViewById(R.id.addMealButton);

        //Get user info from last page
        user =  getIntent().getParcelableExtra("User");

        //Set reviews url
        REVIEWS_URL += user.getUsername();

        //Set menu url
        MENU_URL += user.getUsername();

        chefMenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });

        chefReviews.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();
            }
        });

        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMenu = new Intent(ChefProfile.this, AddChefMenu.class);
                addMenu.putExtra("User", user);
                startActivity(addMenu);
            }
        });

        //Set name
        String name = user.getName();
        chefName.setText(name);

        //Set rating
        chefRating.setRating(user.getRating().floatValue());

        //Set reviews
        reviewList = new ArrayList<>();
        ChefProfileRequests.makeReviewsJSONArrayReq(REVIEWS_URL,this,reviewList,chefReviews);


        //Set menu
        menuList = new ArrayList<>();
        ChefProfileRequests.makeMenuJSONArrayReq(MENU_URL, this, menuList,chefMenu);



    }


}
