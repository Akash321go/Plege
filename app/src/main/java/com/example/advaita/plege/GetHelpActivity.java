package com.example.advaita.plege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.advaita.volley.Request;
import com.example.advaita.volley.RequestQueue;
import com.example.advaita.volley.Response;
import com.example.advaita.volley.VolleyError;
import com.example.advaita.volley.toolbox.StringRequest;
import com.example.advaita.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class GetHelpActivity extends AppCompatActivity implements OnItemSelectedListener{

    public final static String HCITY = "com.example.advaita.plege.HCITY";
    String hcity;

    private static final String REGISTER_URL = "http://simplecoding.16mb.com/ImageUpload/insert.php";

    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_CITY = "city";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DESCRIPTION = "description";

    private EditText fullname;
    private EditText editnumber;
    private EditText editemail_id;
    private EditText editdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);

        Spinner spinner = (Spinner) findViewById(R.id.ngo_spinner);
        spinner.setOnItemSelectedListener(this);

        fullname = (EditText) findViewById(R.id.fullname);
        editnumber = (EditText) findViewById(R.id.number);
        editemail_id = (EditText) findViewById(R.id.email_id);
        editdesc = (EditText) findViewById(R.id.desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_help, menu);
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        hcity = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    private void registerUser(){
        final String city = hcity.trim();
        final String fname = fullname.getText().toString().trim();
        final String phone = editnumber.getText().toString().trim();
        final String email = editemail_id.getText().toString().trim();
        final String description = editdesc.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(GetHelpActivity.this,response,Toast.LENGTH_LONG).show();

                        getinfo();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GetHelpActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put(KEY_FULLNAME,fname);
                params.put(KEY_CITY,city);
                params.put(KEY_PHONE,phone);
                params.put(KEY_EMAIL,email);
                params.put(KEY_DESCRIPTION, description);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        SharedPreferences sharedpreferences;

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("description",description);
        editor.commit();
    }

    public void myCustomMethod(View v){
        registerUser();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.logout) {

            SharedPreferences sharedpreferences;

            sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("username",null);
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getinfo(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
