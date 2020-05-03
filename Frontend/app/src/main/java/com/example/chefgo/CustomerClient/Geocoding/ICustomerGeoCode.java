package com.example.chefgo.CustomerClient.Geocoding;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

public interface ICustomerGeoCode  {
     LatLng getLocationFromAddress(Context context, String inputtedAddress);
}
