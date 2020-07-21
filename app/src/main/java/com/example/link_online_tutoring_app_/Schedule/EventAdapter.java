package com.example.link_online_tutoring_app_.Schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventHolder> {
    Context thisContext;
    ArrayList<ScheduleModel> List;

    public EventAdapter(Context thisContext, ArrayList<ScheduleModel> list) {
        this.thisContext = thisContext;
        List = list;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventholder, null);

        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        EventHolder.Availability.setText(List.get(position).getAvailability());
        EventHolder.Time.setText("Time: " + List.get(position).getTime());
        EventHolder.Day.setText("Day: " + List.get(position).getDay());
        EventHolder.Month.setText("Month: " + List.get(position).getMonth());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
