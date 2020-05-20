package com.example.link_online_tutoring_app_;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import managers.AsyncHTTP;

public class ViewAnswers extends AppCompatActivity {
    RecyclerView Rv;
    Adapter myAd;
    ArrayList<Answer_Model> modelsVR6 = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewanswers);

       String Post_id = getIntent().getStringExtra("post_id");

        Log.d("TheID", Post_id);
        ContentValues cv = new ContentValues();
        cv.put("post_id", Post_id);
        getMyList(cv);


    }
    String Answer;
    String Name;
    int LikesNum;
    private void getMyList(final ContentValues cv) {
        Rv = findViewById(R.id.recycleView);
        Rv.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<Answer_Model> models = new ArrayList<>();

                Log.d("Link", "Entering Link");
                new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/Sort.php", cv) {

                    @Override
                    protected void onPreExecute() {

                    }

                    @Override
                    protected void onPostExecute(String output) {
                        try {
                            JSONArray arr = new JSONArray(output);
                            ArrayList<Answer_Model> models = new ArrayList<>();
                            Answer_Model am;
                            for (int i = 0; i < arr.length(); i++) {
                                final JSONObject ob = (JSONObject) arr.get(i);
                                am = new Answer_Model();
                                Log.d("Repley", "Getting reply");
                                Answer = ob.getString("REPLY");
                                Name = ob.getString("AUTHOR");
                                LikesNum = Integer.parseInt(ob.getString("REPLY_LIKES"));
                                am.setAnswer(Answer);
                                am.setAuthor(Name);
                                am.setLikes(LikesNum);

                                models.add(am);
                                modelsVR6 = models;
                                Log.d("ClassAnswer", modelsVR6.size() + "");
                            }

                            myAd = new Adapter(ViewAnswers.this, models);
                            Rv.setAdapter(myAd);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }.execute();

    }
}
