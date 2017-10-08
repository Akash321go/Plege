package com.example.advaita.plege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void openLogin(View view) {

        String MyPREFERENCES = "MyPrefs";
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String username = sharedpreferences.getString("username", " ");
        String ngo = sharedpreferences.getString("ngo", " ");

        if (username.equals(" ")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    public void openLoginNGO(View view) {

        String MyPREFERENCES = "MyPrefs";
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String ngo = sharedpreferences.getString("ngo", " ");

        if(ngo.equals("yes")){
            Intent intent = new Intent(this, NGOHomeActivity.class);
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(this, NGOLoginActivity.class);
            startActivity(intent);
        }
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

        return super.onOptionsItemSelected(item);
    }
}
