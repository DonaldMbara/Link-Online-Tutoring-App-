package com.example.link_online_tutoring_app_.AptListUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.R;

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
    public void onBindViewHolder(@NonNull MyNameHolder holder, int position) {
            holder.Nameheld.setText(models.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


}
