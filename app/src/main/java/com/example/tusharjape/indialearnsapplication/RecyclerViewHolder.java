package com.example.tusharjape.indialearnsapplication;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    String latitude, longitude, placeName, subjectAvailable;
    String beginLat, beginLong, endLat, endLong;
    RecyclerViewHolder(final View itemView) {
        super(itemView);
        latitude = "";
        longitude = "";

        TextView textView =  itemView.findViewById(R.id.textPlaceName);
        textView.setText(placeName);

        TextView subject = itemView.findViewById(R.id.subjectAvailable);
        subject.setText(subjectAvailable);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WayPointActivity.class);
                intent.putExtra("beginLat", beginLat);
                intent.putExtra("beginLong", beginLong);
                intent.putExtra("endLat", endLat);
                intent.putExtra("endLong", endLong);
                intent.putExtra("centerLat", latitude);
                intent.putExtra("centerLong", longitude);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
