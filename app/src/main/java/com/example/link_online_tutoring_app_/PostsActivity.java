package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.jar.JarException;

import managers.AsyncHTTP;

public class PostsActivity extends AppCompatActivity {
    public static String[] Hold = new String[1];
    Button uploadImage;
    static String Holding;
    static String HoldName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        uploadImage = findViewById(R.id.uploadbtn);
        final Button Post_Button = findViewById(R.id.Post_button);
        final EditText Question = findViewById(R.id.Add_post);
        final Button Selector = findViewById(R.id.CourseChoice);
        final TextView viewer = findViewById(R.id.Viewer);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        final ContentValues cv = new ContentValues();
       final ContentValues cv2 = new ContentValues();
        final ContentValues cv3 = new ContentValues();
        String Selected_Course;



        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(PostsActivity.this,ImageUploadActivity.class));
            }
        });
        Selector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopupMenu pop = new PopupMenu(PostsActivity.this, Selector);
                pop.getMenuInflater().inflate(R.menu.popup_menu,pop.getMenu());
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        viewer.setText(menuItem.getTitle().toString());
                        return true;
                    }
                });
                pop.show();
            }
        });


        Post_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Dummy_Selection = viewer.getText().toString().trim();
                String Q = Question.getText().toString().trim();

                String Safe_checker = "Safe";

                if(TextUtils.isEmpty(Q)){
                    Question.setError("Cannot post an empty field");
                    Safe_checker = "Unsafe";
                }
                if(TextUtils.isEmpty(Dummy_Selection)){
                   Selector.setError("");
                   Safe_checker = "Unsafe";
                }

                if(Safe_checker.equals("Safe")) {

                    SharedPreferences Prefs = LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
                    String StudentNumber = (Prefs.getString("Key", ""));
                    cv2.put("course_name", Dummy_Selection);
                    cv3.put("studentNo", StudentNumber);
                    Get_Course_ID(cv2);
                    Get_Username(cv3);

                    Log.d("Dummy_S", Dummy_Selection);
                    Log.d("Student_N", StudentNumber);
                    String dum = "DummyURL";

                    if (TextUtils.isEmpty(Holding) || TextUtils.isEmpty(HoldName)) {
                        Get_Course_ID(cv2);
                        Get_Username(cv3);
                        Toast toast = Toast.makeText(PostsActivity.this, "Press Again To Confirm", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                        toast.show();
                    } else {

                        Log.d("Wtf", HoldName);
                        cv.put("status", Q);
                        cv.put("postlikes", 0);
                        cv.put("courseid", Holding);
                        cv.put("author", HoldName);
                        cv.put("photoURL", dum);
                        Do_Post(cv, PostsActivity.this);

                    }
                }

            }
        });




    }

    private static String Get_Course_ID(ContentValues cv2){

        new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/getid.php", cv2){

            @Override
            protected void onPreExecute() {
            }
            @Override
            protected void onPostExecute(String output) {
                Log.d("Leo", output);
                try {
                    JSONArray arr = new JSONArray(output);

                         JSONObject op = (JSONObject) arr.get(0);
                         Holding = op.getString("courseID");

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.execute();
        return Holding;
    }


    public static String Get_Username(ContentValues cv3){
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
                    HoldName = op.getString("FirstName");
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }.execute();

        return HoldName;
    }

    private static void Do_Post(ContentValues cv,final Context act ) {

            new AsyncHTTP("https://lamp.ms.wits.ac.za/~s1819369/post.php", cv) {

                @Override
                protected void onPreExecute() {

                }

                @Override
                protected void onPostExecute(String output) {
                    if (output != null && output.equals("Uploaded Successfully")) {
                        Toast toast = Toast.makeText(act, "Uploaded Successfully", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                        toast.show();
                        Log.d("Username", HoldName);
                        Holding = "";
                        HoldName = "";
                        act.startActivity(new Intent(act, HomeActivity.class));
                        ((Activity) act).finish();
                    } else {
                        Toast toast = Toast.makeText(act, output, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 240, 0);
                        toast.show();
                    }
                }
            }.execute();
        }


    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
        PostsActivity.this.startActivity(new Intent(PostsActivity.this,HomeActivity.class));
        finish();
    }
}


