package com.example.tusharjape.indialearnsapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    ArrayList<CenterDetail> list;
    String beginLat, beginLong, endLat, endLong;

    RecyclerView_Adapter(ArrayList<CenterDetail> list, String beginLat, String beginLong, String endLat, String endLong){
        this.list = list;
        this.beginLat = beginLat;
        this.beginLong = beginLong;
        this.endLat = endLat;
        this.endLong = endLong;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final CenterDetail centerDetail = list.get(position);

        holder.latitude = centerDetail.latitude;
        holder.longitude = centerDetail.longitude;
        holder.placeName = centerDetail.placeName;
        holder.subjectAvailable = centerDetail.subjectAvailable;
        holder.cardView = centerDetail.cardView;
        holder.beginLat = beginLat;
        holder.beginLong = beginLong;
        holder.endLat = endLat;
        holder.endLong = endLong;
    }

    @Override
    public int getItemCount()  {
        return (null != list ? list.size() : 0);
    }
}
