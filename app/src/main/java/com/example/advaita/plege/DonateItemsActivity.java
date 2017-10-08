package com.example.advaita.plege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DonateItemsActivity extends AppCompatActivity {

    public final static String CATEGORY = "com.example.advaita.plege.CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_items);
    }

    public void openDonatec(View view) {
        String cat="Clothes";

        Intent intent = new Intent(this, DonateInfoActivity.class);
        intent.putExtra(CATEGORY, cat);
        startActivity(intent);
    }

    public void openDonatet(View view) {
        String cat="Tools";

        Intent intent = new Intent(this, DonateInfoActivity.class);
        intent.putExtra(CATEGORY, cat);
        startActivity(intent);
    }

    public void openDonateu(View view) {
        String cat="Utilities";

        Intent intent = new Intent(this, DonateInfoActivity.class);
        intent.putExtra(CATEGORY, cat);
        startActivity(intent);
    }

    public void openDonatem(View view) {
        String cat="Miscellaneous";

        Intent intent = new Intent(this, DonateInfoActivity.class);
        intent.putExtra(CATEGORY, cat);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donate_items, menu);
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
