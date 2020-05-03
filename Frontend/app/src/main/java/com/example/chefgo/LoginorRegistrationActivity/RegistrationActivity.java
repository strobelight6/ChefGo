package com.example.chefgo.LoginorRegistrationActivity;
/**
 * @author SB_3
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.CustomerClient.CustomerMainActivity;
import com.example.chefgo.R;
import com.example.chefgo.LoginorRegistrationActivity.Validation.ValidationRegister;
import com.example.chefgo.app.AppController;

import org.json.JSONObject;

import static com.example.chefgo.app.AppController.TAG;

public class RegistrationActivity extends AppCompatActivity {
    private Button submit;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private EditText fname;
    private EditText lname;
    private EditText address;
    private EditText zipcode;
    private Spinner state;
    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/users";
    private String URL2 = "http://coms-309-sb-3.misc.iastate.edu:8080/user/check";
    private boolean newUser = true;
    private UsersDomain user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submit = findViewById(R.id.submit);
        submit.setEnabled(true);
        user = new UsersDomain();
        username = findViewById(R.id.username);
        password = findViewById((R.id.password));
        confirmPassword = findViewById(R.id.cPassword);
        email= findViewById(R.id.email);
        fname =findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        address = findViewById(R.id.address);
        zipcode = findViewById(R.id.zipcode);
        state = findViewById(R.id.stateSpinner);

        fillStateSpinner();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              submitRegistration();
            }
        });




    }
    private void submitRegistration() {

             ValidationRegister validator = new ValidationRegister();
            if (!validator.validateEmail(email.getText().toString())) {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!validator.validatePassword(password.getText().toString(), confirmPassword.getText().toString())){
                Toast.makeText(getApplicationContext(),"Passwords dont match", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!validator.validateAddress(this, address.getText().toString(), state.getSelectedItem().toString())){
                Toast.makeText(getApplicationContext(), "Address is not valid", Toast.LENGTH_SHORT).show();
                return;
            }

            String checkURL = URL2 +"/"+username.getText().toString();
            validateNewUser(checkURL);


        if(newUser) {
            Map<String, String> params = new HashMap();
            params.put("username", username.getText().toString());
            params.put("email", email.getText().toString());
            params.put("name", fname.getText().toString() + " " + lname.getText().toString());
            params.put("password", password.getText().toString());
            params.put("userType", "1");
            params.put("rating", "5");
            params.put("address", address.getText().toString());
            params.put("state", state.getSelectedItem().toString());
            params.put("zip", zipcode.getText().toString());



            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Intent customer = new Intent(RegistrationActivity.this, CustomerMainActivity.class);
                    user.setUsername(username.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setName(fname.getText().toString() + " " + lname.getText().toString());
                    user.setAddress(address.getText().toString());
                    user.setState(state.getSelectedItem().toString());
                    user.setZip(Integer.parseInt(zipcode.getText().toString()));
                    user.setPassword(password.getText().toString());
                    user.setRating(5.0);
                    user.setUserType(1);
                    customer.putExtra("User", user);
                    startActivity(customer);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Intent customer = new Intent(RegistrationActivity.this, CustomerMainActivity.class);
                    user.setUsername(username.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setName(fname.getText().toString());
                    user.setAddress(address.getText().toString());
                    user.setState(state.getSelectedItem().toString());
                    user.setZip(Integer.parseInt(zipcode.getText().toString()));
                    user.setPassword(password.getText().toString());
                    user.setRating(5.0);
                    user.setUserType(1);
                    customer.putExtra("User", user);
                    startActivity(customer);
                }
            });

            AppController.getInstance().addToRequestQueue(jsonRequest);
        }
    }

    private void fillStateSpinner(){
    List<String> states = new ArrayList<String>();

    states.add("Alabama");
    states.add("Alaska");
    states.add("Arizona");
    states.add("Arkansas");
    states.add("California");
    states.add("Colorado");
    states.add("Connecticut");
    states.add("Delaware");
    states.add("Florida");
    states.add("Georgia");
    states.add("Hawaii");
    states.add("Idaho");
    states.add("Illinois");
    states.add("Indiana");
    states.add("Iowa");
    states.add("Kansas");
    states.add("Kentucky");
    states.add("Louisiana");
    states.add("Maine");
    states.add("Maryland");
    states.add("Massachusetts");
    states.add("Michigan");
    states.add("Minnesota");
    states.add("Mississippi");
    states.add("Missouri");
    states.add("Montana");
    states.add("Nebraska");
    states.add("Nevada");
    states.add("New Hampshire");
    states.add("New Jersey");
    states.add("New Mexico");
    states.add("New York");
    states.add("North Carolina");
    states.add("North Dakota");
    states.add("Ohio");
    states.add("Oklahoma");
    states.add("Oregon");
    states.add("Pennsylvania");
    states.add("Rhode Island");
    states.add("South Carolina");
    states.add("South Dakota");
    states.add("Tennessee");
    states.add("Texas");
    states.add("Utah");
    states.add("Vermont");
    states.add("Virginia");
    states.add("Washington");
    states.add("West Virginia");
    states.add("Wisconsin");
    states.add("Wyoming");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        state.setAdapter(dataAdapter);
    }
    public void validateNewUser(final String request) {
        // Tag used to cancel the request
        String  tag_string_req = "string_req";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                request, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(response.equals("yes")){
                    newUser = false;
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
}
