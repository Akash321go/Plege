package com.example.advaita.plege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.advaita.volley.RequestQueue;
import com.example.advaita.volley.Response;
import com.example.advaita.volley.VolleyError;
import com.example.advaita.volley.toolbox.StringRequest;
import com.example.advaita.volley.toolbox.Volley;

public class NGOItemsActivity extends AppCompatActivity implements View.OnClickListener{

        public static final String JSON_URL = "http://simplecoding.16mb.com/ImageUpload/get-data.php";

        private Button buttonGet;

        private ListView listView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ngoitems);

            buttonGet = (Button) findViewById(R.id.buttonGet);
            buttonGet.setOnClickListener(this);
            listView = (ListView) findViewById(R.id.listView);
        }

        private void sendRequest(){

            StringRequest stringRequest = new StringRequest(JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            showJSON(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(NGOItemsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        private void showJSON(String json){

            ParseJSON pj = new ParseJSON(json);
            pj.parseJSON();
            CustomList cl = new CustomList(this, ParseJSON.ids,ParseJSON.name,ParseJSON.ngo,ParseJSON.mode);
            listView.setAdapter(cl);
        }

        @Override
        public void onClick(View v) {
            sendRequest();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ngoitems, menu);
        return true;
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
            editor.putString("ngo",null);
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

