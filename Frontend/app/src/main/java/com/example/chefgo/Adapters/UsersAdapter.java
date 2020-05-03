package com.example.chefgo.Adapters;
/**
 * @author SB_3
 *
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.chefgo.R;
import com.example.chefgo.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsersAdapter  extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;



    public UsersAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     *
     * @return size of list
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter, null);
        }


        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));


        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        Button addBtn = (Button)view.findViewById(R.id.add_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String user = list.get(position);
                String url = "http://coms-309-sb-3.misc.iastate.edu:8080/users/";
                String username = "";
                int i =10;
                while(user.charAt(i) != '\n'){
                    username += user.charAt(i);
                    i++;
                }
                url += username  ;
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("userType", String.valueOf(position));
                JSONObject jsonObject = new JSONObject(map);
                JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.DELETE, url, jsonObject,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // response
                                Log.d("Response", response.toString());
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                            }
                        }
                );
                AppController.getInstance().addToRequestQueue(putRequest);

                list.remove(position);

                notifyDataSetChanged();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String[] userTypes = {"Admin", "Customer", "Chef"};
                final int index = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Change User Type");
                builder.setItems(userTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int usertype = which;
                        String url = "http://coms-309-sb-3.misc.iastate.edu:8080/users/userType/";
                            String user = list.get(index);
                            String username = "";
                            int i =10;
                            while(user.charAt(i) != '\n'){
                                username += user.charAt(i);
                                i++;
                            }
                            url += username + "/"+ which;
                        Map<String, String> map = new HashMap<>();
                        map.put("username", username);
                        map.put("userType", String.valueOf(which));
                        JSONObject jsonObject = new JSONObject(map);
                        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,
                                new Response.Listener<JSONObject>()
                                {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // response
                                        Log.d("Response", response.toString());
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Log.d("Error.Response", error.toString());
                                    }
                                }
                        );
                        AppController.getInstance().addToRequestQueue(putRequest);





                    }
                });
                builder.show();
                notifyDataSetChanged();
            }
        });

        return view;
    }
}


