package com.example.saahil.nitcalert;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class ReportAdapter extends ArrayAdapter<Report> {


    private Context mContext;
    private List<Report> reportList = new ArrayList<>();

    public ReportAdapter(@NonNull Context context, ArrayList<Report> list) {
        super(context, 0 , list);
        mContext = context;
        reportList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.report_layout_listview,parent,false);

        Report currentReport = reportList.get(position);

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
        return listItem;
    }

}
