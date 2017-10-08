package com.example.advaita.plege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button sign_up;
    EditText fname, age1, username1, password, confirm_password, email_id;

    Database helper=new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = (EditText) findViewById(R.id.fname);
        age1 = (EditText) findViewById(R.id.age);
        username1 = (EditText) findViewById(R.id.username);
        email_id = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int age=0;

        age= Integer.parseInt(age1.getText().toString());
        String username=username1.getText().toString();
        String name=fname.getText().toString();
        String email=email_id.getText().toString();
        String pass1 = password.getText().toString();
        String pass2 = confirm_password.getText().toString();



        if (v.getId() == R.id.sign_up) {



            if(age==0||name==null||username==null||email==null||pass1==null||pass2==null){
                Toast pass = Toast.makeText(SignupActivity.this, "Form Incomplete", Toast.LENGTH_SHORT);
                pass.show();

            }

            else if (!pass1.equals(pass2)) {

                //Toast pass = Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT);
                //pass.show();

                confirm_password.setError("Password Does Not Match");
                confirm_password.requestFocus();
            }


            else{

                User u=new User();
                u.setAge(age);
                u.setName(name);
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(pass1);

                helper.insertUser(u);

                Toast pass = Toast.makeText(SignupActivity.this, "Registration Complete", Toast.LENGTH_SHORT);
                pass.show();

                Intent intent = new Intent(this, WelcomeActivity.class);
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
