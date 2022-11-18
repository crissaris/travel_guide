package com.example.traveller_guide.utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Service {

    private final String url = "http://localhost:3000";

    public interface VolleyResponseListener{
        void onResponse(JSONArray jsonArray);
        void onResponse(JSONObject jsonObject);
        void onError(String message);
    }

    public void serviceGetJsonObject(Context context, String addUrl, String name, VolleyResponseListener volleyResponseListener){
        String Url = addNameOfRequestToUrl(addUrl, url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, response -> {
            volleyResponseListener.onResponse(response);
        }, error -> {volleyResponseListener.onError(name+" not found!!!" );});

        Singleton.getSingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    public void serviceGetJsonArray(Context context, String addUrl, String name, VolleyResponseListener volleyResponseListener){
        String Url = addNameOfRequestToUrl(addUrl, url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, response -> {
            volleyResponseListener.onResponse(response);
        }, error -> {volleyResponseListener.onError(name+" not found!!!");});
        Singleton.getSingleton(context).addToRequestQueue(jsonArrayRequest);
    }

    public String addNameOfRequestToUrl(String addUrl, String url) {
      String newUrl = "";
      if(addUrl == null) {
          newUrl = url;
      }
      else {
          newUrl = url + "/" + addUrl;
      }
      return newUrl;
    }

}

