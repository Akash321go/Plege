package com.example.advaita.plege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NGOSignupActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedpreferences;

    Button sign_up;
    EditText name,username1, password, confirm_password, email_id;

    Database1 helpers=new Database1(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_ngo);

        name = (EditText) findViewById(R.id.nname);
        username1 = (EditText) findViewById(R.id.username);
        email_id = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String username = username1.getText().toString();
        String nname = name.getText().toString();
        String email = email_id.getText().toString();
        String pass1 = password.getText().toString();
        String pass2 = confirm_password.getText().toString();

        if (v.getId() == R.id.sign_up) {

            if(nname==""||username==""||email==""||pass1==""||pass2==""){
                Toast pass = Toast.makeText(NGOSignupActivity.this, "Please fill in all your details!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if (!pass1.equals(pass2)) {

                //Toast pass = Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT);
                //pass.show();

                confirm_password.setError("Password Does Not Match");
                confirm_password.requestFocus();
            }

            else{
                Ngo u=new Ngo();
                u.setName(nname);
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(pass1);

                helpers.insertNgo(u);

                Toast pass = Toast.makeText(NGOSignupActivity.this, "Registration Complete", Toast.LENGTH_SHORT);
                pass.show();

                sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("username", username);
                editor.putString("ngo", "yes");
                editor.commit();

                Intent intent = new Intent(this, NGOHomeActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
}
