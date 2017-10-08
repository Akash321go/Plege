package com.example.advaita.plege;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {
    public static String[] ids;
    public static String[] name;
    public static String[] ngo;
    public static String[] mode;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "photo";
    public static final String KEY_CAT = "cat";
    public static final String KEY_NGO = "ngo";
    public static final String KEY_MODE = "mode";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            name = new String[users.length()];
            ngo = new String[users.length()];
            mode = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                name[i] = jo.getString(KEY_CAT);
                ngo[i] = jo.getString(KEY_NGO);
                mode[i] = jo.getString(KEY_MODE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}