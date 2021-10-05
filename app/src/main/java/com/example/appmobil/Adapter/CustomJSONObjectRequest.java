package com.example.appmobil.Adapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.appmobil.Interface.VolleyResponse;

import org.json.JSONObject;

public class CustomJSONObjectRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private VolleyResponse volleyResponse;
    private String tag;
    private JsonObjectRequest jsonObjectRequest;


    public CustomJSONObjectRequest(int method, String url, JSONObject jsonObject, String tag, VolleyResponse volleyResponse) {
        this.volleyResponse = volleyResponse;
        this.tag= tag;
        jsonObjectRequest = new JsonObjectRequest(method, url, jsonObject, this, this);
    }

    @Override
    public void onResponse(JSONObject response) {
        volleyResponse.onResponse(response, tag);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        volleyResponse.onError(error, tag);
    }

    public JsonObjectRequest getJsonObjectRequest() {
        return jsonObjectRequest;
    }
}