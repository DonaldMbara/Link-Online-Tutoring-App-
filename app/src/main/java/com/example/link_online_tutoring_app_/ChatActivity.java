package com.example.link_online_tutoring_app_;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import managers.AsyncHTTP;




public class ChatActivity extends AppCompatActivity {

    Button btnSend_, btnReload_;
    TextView receiverName;
    EditText messageET;
    String receiver, receiverStudNum, senderStudNum;
    String message, sender;

    RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    static PostAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        receiverName = findViewById(R.id.receiverName);
        btnReload_ = findViewById(R.id.btnReload);
        btnSend_ = findViewById(R.id.btnSend);

        messageET = findViewById(R.id.messageEText);

        Intent intent = getIntent();
        receiver = intent.getStringExtra("receiver");
        receiverName.setText((receiver));

        recyclerView = findViewById(R.id.rvMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void sendMessage(View view) {
        message = messageET.getText().toString().trim();

        SharedPreferences Prefs = LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
        senderStudNum = (Prefs.getString("Key2", ""));
        receiverStudNum = "1111";
        sender = "Donald";


        boolean checkMyThings = true;
        if (TextUtils.isEmpty(message)) {
            messageET.setError("Enter Your Message");
            checkMyThings = false;
        }
        if (checkMyThings == true) {

            new RequestHandler(ChatActivity.this, "send message").execute(message, sender, receiver, receiverStudNum, senderStudNum);
            messageET.getText().clear();

        } else {

            Toast toast = Toast.makeText(ChatActivity.this, "Please type something...", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}

