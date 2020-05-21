package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;

import managers.AsyncHTTP;

public class AnswerActivity extends AppCompatActivity {
    String The_Post_Id;
   static String Author;
    String Status;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.answer_ctivity);
        SharedPreferences Prefs = LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
        String StudentNumber = (Prefs.getString("Key2", ""));
        final EditText TheAnswer = findViewById(R.id.Add_Ans);
        final Button TheAns_btn = findViewById(R.id.Answer_button);
        The_Post_Id = getIntent().getStringExtra("Post_Id_Key");
        Log.d("the_id", The_Post_Id);
        final ContentValues getname = new ContentValues();
        final ContentValues sendAns = new ContentValues();
        getname.put("studentNo",StudentNumber);
        Get_TheName(getname);


        TheAns_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Status = TheAnswer.getText().toString().trim();
                String Safe_checker = "Safe";

                if(TextUtils.isEmpty(Status)){
                    TheAnswer.setError("Cannot post an empty field");
                    Safe_checker = "Unsafe";
                }

                if(Safe_checker.equals("Safe")) {

                    if (TextUtils.isEmpty(Author) || TextUtils.isEmpty(The_Post_Id)) {
                        Get_TheName(getname);
                        Toast toast = Toast.makeText(AnswerActivity.this, "Press Again To Confirm", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                        toast.show();
                    } else {


                        sendAns.put("post_id", The_Post_Id);
                        sendAns.put("author", Author);
                        sendAns.put("answer", Status);

                        AddAnswer(sendAns);

                    }
                }
            }
        });


    }


    public static String Get_TheName(ContentValues cv3){
        String SomeString;
        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/getName.php", cv3){

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray arr = new JSONArray(output);
                    JSONObject op = (JSONObject) arr.get(0);
                    Author = op.getString("FirstName");
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }.execute();

        return Author;
    }

    public void AddAnswer(ContentValues cv){
        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/addAns.php", cv) {
            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                if (output != null && output.equals("Uploaded Successfully")) {
                    Toast toast = Toast.makeText(AnswerActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                    toast.show();
                    The_Post_Id = "";
                    Author = "";
                    AnswerActivity.this.startActivity(new Intent(AnswerActivity.this, ViewPosts.class));
                    finish();
                } else {
                    Toast toast = Toast.makeText(AnswerActivity.this, output, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                    toast.show();
                }
            }
        }.execute();
    }
}
