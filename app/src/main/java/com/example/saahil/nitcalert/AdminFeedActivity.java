package com.example.saahil.nitcalert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminFeedActivity extends AppCompatActivity {

    private ListView listView;
    ReportAdapterAdmin adapter;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.report_list);
        final ArrayList<Report> reportList = new ArrayList<>();

//        Button logout = (Button) findViewById(R.id.log_out);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
        db = FirebaseDatabase.getInstance().getReference("feed");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Report r = ds.getValue(Report.class);
                    System.out.println(r.getDescription());
                    System.out.println(r.getContact_of_reporter());
                    reportList.add(r);
                }
                System.out.println(reportList.size());
                adapter = new ReportAdapterAdmin(AdminFeedActivity.this, reportList);
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
        getMenuInflater().inflate(R.menu.menu_main_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(AdminFeedActivity.this, AddEmergency.class);
                startActivity(intent);
                return true;

            case R.id.edit_contact:
                Intent intent2 = new Intent(AdminFeedActivity.this, EditContact.class);
                System.out.print("Here");
                startActivity(intent2);
                return true;

            case R.id.call:
                Intent inten = new Intent(AdminFeedActivity.this, Call_emergency.class);
                startActivity(inten);
                return true;


        }
        return (super.onOptionsItemSelected(item));
    }
}