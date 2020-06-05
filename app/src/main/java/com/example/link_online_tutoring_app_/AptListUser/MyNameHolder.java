package com.example.link_online_tutoring_app_.AptListUser;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.link_online_tutoring_app_.R;

public class MyNameHolder extends RecyclerView.ViewHolder {
    TextView Nameheld;
    Button Schedule_button;
//    onNameListener ONL;
    public MyNameHolder(@NonNull View itemView) {
        super(itemView);
        this.Nameheld = itemView.findViewById(R.id.ShowName);
        this.Schedule_button = itemView.findViewById(R.id.schedule_btn);
    }


}
