package com.example.geforce.nitcalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add_emergency(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, AddEmergency.class);
        startActivity(intent);
    }
}
