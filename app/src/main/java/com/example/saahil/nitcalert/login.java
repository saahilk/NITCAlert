package com.example.saahil.nitcalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void gotoadminfeed(View v) {

        EditText uname = (EditText) findViewById(R.id.username);
        String unam = uname.getText().toString();

        EditText passwd = (EditText) findViewById(R.id.password);
        String pwd = passwd.getText().toString();
        System.out.println(pwd);

        if(unam.equals("admin") && pwd.equals("admin")){
            Intent intent = new Intent(login.this, AdminFeedActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "wrong password!!", Toast.LENGTH_LONG).show();
        }
    }

    }
