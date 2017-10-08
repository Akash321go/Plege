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
import android.widget.Spinner;

public class DonateInfoActivity extends AppCompatActivity implements OnItemSelectedListener{

    public final static String CITY = "com.example.advaita.plege.CITY";
    public final static String MODE = "com.example.advaita.plege.MODE";
    public final static String CATEGORY = "com.example.advaita.plege.CATEGORY";

    String item,city,mode,cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_info);

        Intent intent = getIntent();
        cat = intent.getStringExtra(DonateItemsActivity.CATEGORY);

        // Spinner element
        Spinner spinner1 = (Spinner) findViewById(R.id.ngo_spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.del_spinner);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donate_info, menu);
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (parent.getId()) {
            case R.id.ngo_spinner:
                city = parent.getItemAtPosition(pos).toString();
                break;
            case R.id.del_spinner:
                mode = parent.getItemAtPosition(pos).toString();
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void openDonateActivity(View view) {
        Intent intent = new Intent(this, DonateActivity.class);

        intent.putExtra(CITY, city);
        intent.putExtra(MODE, mode);
        intent.putExtra(CATEGORY, cat);
        startActivity(intent);
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
}
