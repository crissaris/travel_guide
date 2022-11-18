package com.example.traveller_guide.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    public interface ParsingJson {
        void getValue(String value);
        void getValue(ArrayList<String> values);

    }

    public void getStringFromJsonObject(JSONObject jsonObject, String name, ParsingJson parsingJson) {
        try {
            parsingJson.getValue(jsonObject.getString(name));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getArrayStringFromJsonArray(JSONArray jsonArray, String name, ParsingJson parsingJson) {
        ArrayList<String> array = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                array.add(i, jsonObject.getString(name));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        parsingJson.getValue(array);
    }
}


