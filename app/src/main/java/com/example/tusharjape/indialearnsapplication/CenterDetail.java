package com.example.tusharjape.indialearnsapplication;

import android.support.v7.widget.CardView;

class CenterDetail {

    CardView cardView;
    String latitude, longitude, placeName, subjectAvailable, id;
    double travelTime;
    double distance;
    CenterDetail(String lat, String longi, String id){
        this.latitude = lat;
        this.longitude = longi;
        this.id = id;
        travelTime = 0;
        distance = 0;
    }
}
