package com.example.chefgo.CustomerClient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.chefgo.CustomerClient.CustomerRequests.MapsRequests;
import com.example.chefgo.CustomerClient.Geocoding.CustomerGeoCode;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private UsersDomain user;
    private String CHEFS_URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users/chefs/";
    private List<UsersDomain> chefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_maps);
        chefs = new ArrayList<>();
        user = getIntent().getParcelableExtra("User");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        CHEFS_URL += user.getZip();
        MapsRequests.getJSONArrayRequest(getApplicationContext(), mapFragment, this, CHEFS_URL, (ArrayList<UsersDomain>) chefs);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        CustomerGeoCode geocode = new CustomerGeoCode();
        LatLng currentUserLocation = geocode.getLocationFromAddress(this, user.getAddress()+ ", " +user.getState() );

        googleMap.addMarker(new MarkerOptions().position(currentUserLocation).title("MyHome").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentUserLocation,17));

        for(int i =0;i<chefs.size();i++){
            LatLng chefHomeLocation = geocode.getLocationFromAddress(this, chefs.get(i).getAddress() + ", " + chefs.get(i).getState());
            googleMap.addMarker((new MarkerOptions().position(chefHomeLocation).title(i+ ") " +chefs.get(i).getUsername())));
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker arg0) {
                // Use the Builder class for convenient dialog construction
                String chefIndex = arg0.getTitle().charAt(0) + "";
                final UsersDomain chef = chefs.get(Integer.parseInt(chefIndex));

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerMapsActivity.this);
                builder.setMessage("See "+ chef.getName()+  " Profile?" + "\n" +"Rating: " + chef.getRating())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent chefProfile = new Intent(CustomerMapsActivity.this, CustomerSeeChefProfile.class);
                                chefProfile.putExtra("user", chef);
                                startActivity(chefProfile);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.show();
                return true;
            }// if marker source is clicked, display toast
        });
    }

}
