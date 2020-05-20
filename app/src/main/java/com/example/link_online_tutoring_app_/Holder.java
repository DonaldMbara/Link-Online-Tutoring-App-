package com.example.link_online_tutoring_app_;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    TextView Ans;
    TextView Author;
    TextView Likes;
    Button Like_BTN;

    public Holder(@NonNull View itemView) {
        super(itemView);
        this.Ans = itemView.findViewById(R.id.answer);
        this.Author = itemView.findViewById(R.id.author);
        this.Likes = itemView.findViewById(R.id.num_of_likes);
        this.Like_BTN = itemView.findViewById(R.id.Like_button);
    }
}
