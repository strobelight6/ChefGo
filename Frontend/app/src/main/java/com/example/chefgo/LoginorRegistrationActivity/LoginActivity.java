package com.example.chefgo.LoginorRegistrationActivity;
/**
 * @author SB_3
 *
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.AdminClient.AdminActivity;
import com.example.chefgo.ChefClient.ChefMainActivity;
import com.example.chefgo.DomainObjects.UsersDomain;
import com.example.chefgo.CustomerClient.CustomerMainActivity;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;



import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;



import static com.example.chefgo.app.AppController.TAG;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private String URL = "http://coms-309-sb-3.misc.iastate.edu:8080/user";
    private String notficationWS = "ws://coms-309-sb-3.misc.iastate.edu:8080/notification/";
   //private String URL = "http://10.0.2.2:8080/user";
    public static UsersDomain user = new UsersDomain();
    private EditText username;
    private EditText password;
    private Button register;
    private OkHttpClient client;
    public static WebSocket ws;
    public static String chatOID;
    private static int messageSeq = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        client = new OkHttpClient();



        //region login
        login = findViewById(R.id.login);
        login.setEnabled(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);
                String request = URL + "/";
                request += username.getText().toString();
                makeJSONObjectReq(request);
            }
        });
        //endregion

        //region register
        register = findViewById(R.id.register);
        register.setEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent register = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(register);
        }
        });
        //endregion



    }




    private void makeJSONObjectReq(final String request) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                request, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    user.setUsername(response.getString("username"));
                    user.setEmail(response.getString("email"));
                    user.setName(response.getString("name"));
                    user.setAddress(response.getString("address"));
                    user.setState(response.getString("state"));
                    user.setZip(response.getInt("zip"));
                    user.setPassword(response.getString("password"));
                    user.setRating(response.getDouble("rating"));
                    user.setUserType(response.getInt("userType"));

                    if (user.getPassword().equals(password.getText().toString())) {



                        start(user.getUsername());
                        //if user is an admin
                        if (user.getUserType() == 0) {
                            Intent admin = new Intent(LoginActivity.this, AdminActivity.class);
                            admin.putExtra("User", user);
                            startActivity(admin);
                        }
                        //if user is a customer
                        else if (user.getUserType() == 1) {
                            Intent customer = new Intent(LoginActivity.this, CustomerMainActivity.class);
                            customer.putExtra("User", user);
                            startActivity(customer);
                        }
                        // if user is a chef
                        else if (user.getUserType() == 2) {
                            Intent chef = new Intent(LoginActivity.this, ChefMainActivity.class);
                            chef.putExtra("User", user);
                            startActivity(chef);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Wrong Password: ",
                                Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e) {
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
                        "Wrong password", Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void start(String username) {
        okhttp3.Request request = new okhttp3.Request.Builder().url(notficationWS + username).build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        ws = client.newWebSocket(request, listener);
    }

    public final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        public void onOpen() {

        }
        @Override
        public void onMessage(WebSocket webSocket, String message) {
            if(messageSeq == 0) {
                chatOID = message;
                messageSeq++;
            }
            else{
                AppController.sendNotification(message);
                messageSeq = 0;
            }
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);

        }

        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

        }
    }







}
