package com.example.lockpocket.account;

import android.util.Log;
import android.widget.Toast;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lockpocket.utils.AppNetwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    // final static private String URL;
    private Map<String, String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener, Context context) throws IOException {
        super(Method.POST,
                "http://" + AppNetwork.getServerIp(context) + "/data.jsp", listener, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        Log.d("Network: ", "http://" + AppNetwork.getServerIp(context) + "/data.jsp");

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("accessType", "login");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
