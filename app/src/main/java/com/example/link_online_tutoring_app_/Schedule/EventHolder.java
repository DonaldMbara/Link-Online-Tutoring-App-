package com.example.link_online_tutoring_app_.Schedule;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.R;

public class EventHolder extends RecyclerView.ViewHolder{
    static TextView Availability;
    static TextView Time;
    static TextView Day;
    static TextView Month;

    public EventHolder(@NonNull View itemView) {
        super(itemView);
        this.Time = itemView.findViewById(R.id.time);
        this.Day = itemView.findViewById(R.id.day);
        this.Availability = itemView.findViewById(R.id.availability);
        this.Month = itemView.findViewById(R.id.month);
    }
}
