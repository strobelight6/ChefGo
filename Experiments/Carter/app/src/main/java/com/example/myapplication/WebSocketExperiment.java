package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketExperiment extends AppCompatActivity {

    private String username, userData;
    private static final String URL = "ws://coms-309-sb-3.misc.iastate.edu:8080/getUser/";
    private Button start;
    private TextView output;
    private EditText input;
    private OkHttpClient client;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("");
            //webSocket.close(NORMAL_CLOSURE_STATUS, "Exit success");
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            userData = "";
            try {
                JSONObject obj = new JSONObject(text);
                userData += ("Name: " + obj.getString("name") + "\n");
                userData += ("Username: " + obj.getString("username") + "\n");
                userData += ("Password: " + obj.getString("password") + "\n");
                userData += ("Email: " + obj.getString("email") + "\n");
                userData += ("User type: " + obj.getString("userType") + "\n");
                userData += ("Rating: " + obj.getString("rating") + "\n");
                userData += ("Address: " + obj.getString("address") + "\n");
                userData += ("State: " + obj.getString("state") + "\n");
                userData += ("Zip code: " + obj.getString("zip") + "\n");
                output(userData);
            } catch (JSONException e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            }
            //output("Receiving: " + text);
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing: " + code + " / " + reason);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error: " + t.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket_experiment);

        start = findViewById(R.id.start);
        output = findViewById(R.id.output);
        input = findViewById(R.id.input);
        client = new OkHttpClient();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = input.getText().toString();
                input.setText("");
                start();
            }
        });
    }
    private void start() {
        Request request = new Request.Builder().url(URL + username).build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            String newOutput = output.getText().toString() + "\n\n" + txt;
            @Override
            public void run() {
                output.setText(newOutput);
            }
        });
    }
}