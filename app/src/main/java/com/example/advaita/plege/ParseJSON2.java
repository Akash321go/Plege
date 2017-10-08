package com.example.advaita.plege;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON2 {

    public static String[] name;
    public static String[] city;
    public static String[] phone;
    public static String[] email;
    public static String[] desc;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_NAME = "fullname";
    public static final String KEY_CITY = "city";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DESC = "description";

    private JSONArray users = null;

    private String json;

    public ParseJSON2(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            name = new String[users.length()];
            city = new String[users.length()];
            phone = new String[users.length()];
            email = new String[users.length()];
            desc = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                name[i] = jo.getString(KEY_NAME);
                city[i] = jo.getString(KEY_CITY);
                phone[i] = jo.getString(KEY_PHONE);
                email[i] = jo.getString(KEY_EMAIL);
                desc[i] = jo.getString(KEY_DESC);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}