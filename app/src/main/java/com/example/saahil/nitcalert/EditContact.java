package com.example.saahil.nitcalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello");
        setContentView(R.layout.activity_edit_contact);
    }

    public void change_contact(View view)
    {
        Spinner uname = findViewById(R.id.spinner);
        String unam = uname.getSelectedItem().toString();

        EditText num = findViewById(R.id.contact_num);
        String contact = num.getText().toString();
        System.out.print(unam);
        System.out.println(contact);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");

        myRef.child(unam).setValue(contact);

        Toast.makeText(getApplicationContext(), "Contact updated", Toast.LENGTH_LONG).show();

        Intent intent=new Intent(EditContact.this,AdminFeedActivity.class);
        startActivity(intent);

    }
}
