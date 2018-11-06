package com.example.saahil.nitcalert;

public class Report {
    private String contact_of_reporter;
    private String description;
    private String people_involved;
    private String report_to;
    private String latitude;
    private String longitude;


    private Report(){}

    public Report(String contact_of_reporter,String description,String people_involved,String report_to,String latitude,String longitude){

        this.contact_of_reporter = contact_of_reporter;
        this.description = description;
        this.people_involved = people_involved;
        this.report_to = report_to;
        this.latitude=latitude;
        this.longitude=longitude;

    }


    public String getContact_of_reporter() {
        return contact_of_reporter;
    }

    public void setContact_of_reporter(String contact_of_reporter) {
        this.contact_of_reporter = contact_of_reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeople_involved() {
        return people_involved;
    }

    public void setPeople_involved(String people_involved) {
        this.people_involved = people_involved;
    }

    public String getReport_to() {
        return report_to;
    }

    public void setReport_to(String report_to) {
        this.report_to = report_to;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
