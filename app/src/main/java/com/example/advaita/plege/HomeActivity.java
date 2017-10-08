package com.example.advaita.plege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void openGetHelp(View view) {

        String MyPREFERENCES = "MyPrefs";
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String desc = sharedpreferences.getString("description", " ");

        if (desc.equals(" ")) {
            Intent intent = new Intent(this, GetHelpActivity.class);
            startActivity(intent);
        }

        else{
            Intent intent = new Intent(this, GetHelpActivity2.class);
            startActivity(intent);
        }
    }

    public void openDonateItems(View view) {
        Intent intent = new Intent(this, DonateItemsActivity.class);
        startActivity(intent);
    }

    public void openGiveHelp(View view) {
        Intent intent = new Intent(this, GiveHelpActivity.class);
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
