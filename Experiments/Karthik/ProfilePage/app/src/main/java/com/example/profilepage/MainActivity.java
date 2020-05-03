package com.example.profilepage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {


    ImageView profilePic;
    Button profile;
    private static final int GET_FROM_GALLERY = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.Create);
        profile = findViewById(R.id.profile);



       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String url = "http://10.0.2.2:8080/users";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "POST error");
                    }
                }) {

                @Override
                protected Map<String, String> getParams() {
                    EditText name = findViewById(R.id.Name);
                    Switch cook = findViewById(R.id.Cook);
                    EditText email = findViewById(R.id.Email);
                    EditText number = findViewById(R.id.phoneNumber);
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", "");
                    params.put("cook", Boolean.toString(cook.isChecked()));
                    params.put("email", email.getText().toString());
                    params.put("phone", number.getText().toString());
                    params.put("username", name.getText().toString());
                    return params;
                }
            };

                RequestSingleton.getInstance(MainActivity.this).addToQueue(postRequest);

            }
        });*/




        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == GET_FROM_GALLERY && resultCode == RESULT_OK && intent != null) {
            Uri selectedImage = intent.getData();
            profilePic = findViewById(R.id.profilePic);
            profilePic.setImageURI(selectedImage);
        }
    }

}
