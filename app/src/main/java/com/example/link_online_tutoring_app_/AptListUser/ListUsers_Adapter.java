package com.example.link_online_tutoring_app_.AptListUser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.R;
import com.example.link_online_tutoring_app_.Schedule.ScheduleActivity;

import java.util.ArrayList;

public class ListUsers_Adapter extends RecyclerView.Adapter<MyNameHolder> {
    Context c;
    ArrayList<Name_Model> models;
//     private MyNameHolder.onNameListener mONL;


    public ListUsers_Adapter(Context c, ArrayList<Name_Model> models) {
        this.c = c;
        this.models = models;
//        this.mONL = ONL;
    }



    @NonNull
    @Override
    public MyNameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_holder,null);
        return new MyNameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNameHolder holder, final  int position) {
            holder.Nameheld.setText(models.get(position).getName());
            holder.Schedule_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Apt_ListUsers.ss = models.get(position).getName();
                    Intent it = new Intent(c, ScheduleActivity.class);
                    it.putExtra("Name",  Apt_ListUsers.ss);
                    c.startActivity(it);

                }
            });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


}
