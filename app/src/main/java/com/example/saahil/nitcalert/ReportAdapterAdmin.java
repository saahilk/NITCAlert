package com.example.saahil.nitcalert;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class ReportAdapterAdmin  extends ArrayAdapter<Report> {

    private Context mContext;
    private List<Report> reportList = new ArrayList<>();

    public ReportAdapterAdmin(@NonNull Context context, ArrayList<Report> list) {
        super(context, 0 , list);
        mContext = context;
        reportList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.report_layout_admin,parent,false);

        final Report currentReport = reportList.get(position);

        TextView contact = listItem.findViewById(R.id.contact_of_reporter);
        contact.setText(currentReport.getContact_of_reporter());

        TextView desc =  listItem.findViewById(R.id.description);
        desc.setText(currentReport.getDescription());

        TextView num_people= listItem.findViewById(R.id.num_people);
        num_people.setText(currentReport.getPeople_involved());

        TextView latitude= listItem.findViewById(R.id.latitude);
        latitude.setText(currentReport.getLatitude());

        TextView longitude= listItem.findViewById(R.id.longitude);
        longitude.setText(currentReport.getLongitude());

        TextView loc= listItem.findViewById(R.id.location);

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());
        Double lat=parseDouble(currentReport.getLatitude());
        Double lon=parseDouble(currentReport.getLongitude());

        try{
            addresses = geocoder.getFromLocation(lat,lon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            loc.setText(addresses.get(0).getAdminArea());
        }
        catch (IOException ie)
        {
            ie.printStackTrace();
        }


        Button complete = (Button) listItem.findViewById(R.id.complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref  = FirebaseDatabase.getInstance().getReference();
                Query refQuery  = ref.child("feed").orderByChild("contact_of_reporter").equalTo(currentReport.getContact_of_reporter());
                refQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                            ds.getRef().removeValue();
                        Intent intent = new Intent(getContext(), AdminFeedActivity.class);
                        getContext().startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return listItem;
    }

}
