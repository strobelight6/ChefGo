package com.example.chefgo.app;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.chefgo.Chat.ChatActivity;
import com.example.chefgo.R;
import com.example.chefgo.net_util.LruBitmapCache;



public class AppController extends Application {
    public static final String TAG = AppController.class
            .getSimpleName();
    public static final String CHANNEL_1_ID = "ChefGo";
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;
    public static Context appContext;
    public String http = "http://10.0.2.2:8082/orderHistory";
    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, "ChefGo", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Your order has bee accepted");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
        appContext = getApplicationContext();

        mInstance = this;

    }
    public static synchronized AppController getInstance() {
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void sendNotification(String notifMessage){
        Intent intent = new Intent(appContext, ChatActivity.class);
        PendingIntent chat = PendingIntent.getActivity(appContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification orderAccepted = new NotificationCompat.Builder(appContext, CHANNEL_1_ID).setSmallIcon(R.drawable.ic_spatula)
                .setContentTitle("Order Accepted")
                .setContentText(notifMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(chat)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(appContext);
        notificationManager.notify(1, orderAccepted);
    }
}
