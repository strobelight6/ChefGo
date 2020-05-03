package com.example.profilepage;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestSingleton {
    private static RequestSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;


    private RequestSingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized RequestSingleton getInstance(Context context) {
        if(instance == null) {
            instance = new RequestSingleton(context);
        }
        return instance;
    }

    public void addToQueue(Request request){
        requestQueue.add(request);
    }
}
