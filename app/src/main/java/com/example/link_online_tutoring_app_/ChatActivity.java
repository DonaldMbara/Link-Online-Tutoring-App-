package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {


    ArrayList<Message> listMessage = new ArrayList<Message>();
    RecyclerView rvMessages;

    Button btnSend, btnReload;
    TextView senderName;
    EditText message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        senderName = findViewById(R.id.receiverName);

        Intent intent = getIntent();
        String receiverName = intent.getStringExtra("receiver");
        senderName.setText((receiverName));



    }
}
