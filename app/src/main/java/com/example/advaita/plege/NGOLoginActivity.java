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
import android.widget.EditText;
import android.widget.Toast;

public class NGOLoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etusername, etPassword;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    Database1 helpers=new Database1(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ngo);

        etusername=(EditText) findViewById(R.id.username);
        etPassword=(EditText) findViewById(R.id.password);
        bLogin=(Button) findViewById(R.id.sign_in);

        bLogin.setOnClickListener(this);

    }

    public void openSignup(View view) {
        Intent intent = new Intent(this, NGOSignupActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){

        if(v.getId()==R.id.sign_in) {

            String str = etusername.getText().toString();
            String pass = etPassword.getText().toString();

            String password = helpers.searchPass(str);

            if (pass.equals(password)) {

                sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("username",str);
                editor.putString("ngo","yes");
                editor.commit();

                Intent i = new Intent(NGOLoginActivity.this, NGOHomeActivity.class);
                i.putExtra("Username",str);
                startActivity(i);
            }

            else {
                Toast temp = Toast.makeText(NGOLoginActivity.this, password, Toast.LENGTH_SHORT);
                temp.show();
            }
        }
    }
}
