package com.example.chefgo.CustomerClient;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.ChefSeeProfileRequests;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;

public class CustomerSeeChefProfile extends AppCompatActivity {

    //Page components
    TextView nameView;
    RatingBar ratingBar;
    ListView menuList;
    ListView reviewsList;

    //Private
    private UsersDomain user;
    private String REVIEWS_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/reviewee/";
    private String MENU_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/menus/chef/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_chef_profile);

        //Find views
        nameView = findViewById(R.id.nameView);
        ratingBar = findViewById(R.id.ratingBar);
        menuList = findViewById(R.id.menuList);
        reviewsList = findViewById(R.id.reviewsList);

        //Get username from last page
        user = getIntent().getParcelableExtra("user");

        //Include username in url
        REVIEWS_URL += user.getUsername();
        MENU_URL += user.getUsername();

        //Set fields
        nameView.setText(user.getName());
        ratingBar.setRating(user.getRating().floatValue());

        //Make requests
        ChefSeeProfileRequests.makeReviewsJSONArrayReq(this.getApplicationContext(), REVIEWS_URL, reviewsList);
        ChefSeeProfileRequests.makeMenuJSONArrayReq(this.getApplicationContext(), MENU_URL, menuList);
    }

}
