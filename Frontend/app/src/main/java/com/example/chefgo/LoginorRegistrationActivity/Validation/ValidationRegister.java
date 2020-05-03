package com.example.chefgo.LoginorRegistrationActivity.Validation;
/**
 * @author SB_3
 *
 */

import android.content.Context;

import com.example.chefgo.CustomerClient.Geocoding.CustomerGeoCode;

public class ValidationRegister implements IValidationRegister{

   public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    /**
     * validates email to make sure user used some sort of valid email
     * @param email  typed email of user
     * @return true if valid email or false
     */

    public  boolean validateEmail(String email){
            return email.trim().matches(emailPattern);
        }

    /**
     *
     * @param password string of password field
     * @param confirmPassword string of confirmPassword field
     * @return true if they match, false otherwise
     */
        public  boolean validatePassword(String password, String confirmPassword){
            if (password != null) {

                return password.equals(confirmPassword) ;
            }
            else{
                return false;
            }
        }

        public  boolean validateAddress(final Context ctx, String address, String state){
            CustomerGeoCode geoCode = new CustomerGeoCode();

            return geoCode.getLocationFromAddress(ctx, address + ", "+state) != null;
        }

}
