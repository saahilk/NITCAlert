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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class AddEmergency extends AppCompatActivity {

    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    LocationListener locationListener;
    private String currentLat, currentLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String TAG = "test";
        Log.d(TAG,"In click begin");
        setContentView(R.layout.activity_add_emergency);


        Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddEmergency.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.authorities));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(myAdapter);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "test";
                Log.d(TAG,"In click");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("feed");
                DatabaseReference ref = myRef.push();

                EditText nopeopl = (EditText) findViewById(R.id.nofpeople);
                String num = nopeopl.getText().toString();

                EditText contact = (EditText) findViewById(R.id.contact);
                String cont = contact.getText().toString();



                EditText descp = (EditText) findViewById(R.id.description_title);
                String des = descp.getText().toString();


                Spinner reportto = (Spinner) findViewById(R.id.category_spinner);

                if(!num.equals("") && !des.equals("")) {
                    ref.child("description").setValue(des);
                    descp.setText("");
                    ref.child("people_involved").setValue(num);
                    nopeopl.setText("");
                    ref.child("contact_of_reporter").setValue(cont);
                    contact.setText("");
                    String reto = reportto.getSelectedItem().toString();
                    ref.child("report_to").setValue(reto);
                    ref.child("latitude").setValue(currentLat);
                    ref.child("longitude").setValue(currentLong);
                    // Toast.makeText(AddEmergency.this, currentLat, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddEmergency.this, MainActivity.class);
                    startActivity(intent);

                }
                else
                    Toast.makeText(getApplicationContext(), "Please fill the required fields!!",
                            Toast.LENGTH_LONG).show();
            }
        });


    }

    public void showMyLocation(View v) {
        Toast.makeText(this, "asjdh", Toast.LENGTH_SHORT).show();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality() + ":";
                    result += addresses.get(0).getCountryName();
                    LatLng latLng = new LatLng(latitude, longitude);
                    currentLat = String.valueOf(latLng.latitude);
                    currentLong = String.valueOf(latLng.longitude);
                    String l = latLng.toString();
                    Toast.makeText(AddEmergency.this, latLng.toString(), Toast.LENGTH_SHORT).show();


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }
}
