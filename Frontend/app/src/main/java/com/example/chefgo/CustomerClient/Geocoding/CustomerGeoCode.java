package com.example.chefgo.CustomerClient.Geocoding;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;


import java.io.IOException;
import java.util.List;

public class CustomerGeoCode implements ICustomerGeoCode{

    public LatLng getLocationFromAddress(Context context, String inputtedAddress) throws NullPointerException {
        //consrtuct that geo coder
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        //LatLng resLatLng = null;

        LatLng resLatLng = null;
        try {

            address = coder.getFromLocationName(inputtedAddress, 5);
            if (address == null) {
                return null;
            }
            //way out west smh
            if (address.size() == 0) {
                return null;
            }
            //get address
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            resLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return resLatLng;
    }
}
