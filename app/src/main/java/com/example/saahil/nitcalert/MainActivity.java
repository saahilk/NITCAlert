package com.example.saahil.nitcalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ReportAdapter adapter;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.report_list);
        final ArrayList<Report> reportList=new ArrayList<>();

        db= FirebaseDatabase.getInstance().getReference("feed");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                   Report r=ds.getValue(Report.class);
                   System.out.println(r.getDescription());
                   System.out.println(r.getContact_of_reporter());
                   reportList.add(r);
                }
                System.out.println(reportList.size());
                adapter = new ReportAdapter(MainActivity.this,reportList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        System.out.println(reportList.size());


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add:
            Intent intent = new Intent(MainActivity.this, AddEmergency.class);
            startActivity(intent);
            return true;

        case R.id.call:
            Intent inten = new Intent(MainActivity.this, Call_emergency.class);
            startActivity(inten);
            return true;

        case R.id.login_admin:
            Intent intent2=new Intent(MainActivity.this, login.class);
            startActivity(intent2);
            return true;

    }
        return(super.onOptionsItemSelected(item));
    }

    public void add_emergency(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, AddEmergency.class);
        startActivity(intent);
    }

    public void call_emergency(View view) {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, Call_emergency.class);
        startActivity(intent);
    }
}


