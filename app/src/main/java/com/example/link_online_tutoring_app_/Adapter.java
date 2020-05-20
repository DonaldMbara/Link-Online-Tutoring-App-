package com.example.link_online_tutoring_app_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {
    Context c;
    ArrayList<Answer_Model> models;

    public Adapter(Context c, ArrayList<Answer_Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postsholder, null);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.Ans.setText(models.get(position).getAnswer());
        holder.Author.setText("Author: "+ models.get(position).getAuthor());
        holder.Likes.setText("Likes: " + models.get(position).getLikes());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
