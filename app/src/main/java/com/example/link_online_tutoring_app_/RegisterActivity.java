package com.example.link_online_tutoring_app_;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import managers.AsyncHTTP;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //final EditText idNum = findViewById(R.id.idNumTxt);
        final EditText userName = findViewById(R.id.userNameTxt);
        final EditText firstName = findViewById(R.id.fNameTxt);
        final EditText lastName = findViewById(R.id.lNameTxt);
        final EditText email = findViewById(R.id.emailTxt);
        final EditText studentNumber = findViewById(R.id.stdNumTxt);
        final EditText password = findViewById(R.id.passTxt);
        final Button confirmReg = findViewById(R.id.button2);



        confirmReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String UserName = userName.getText().toString().trim();
                final String FirstName = firstName.getText().toString().trim();
                final String LastName = lastName.getText().toString().trim();
                final String Email = email.getText().toString().trim();
                final String StudentNumber = studentNumber.getText().toString().trim();
                final String Password = password.getText().toString().trim();
                //int stdNum = Integer.parseInt(StudentNumber);
                boolean checkMyThings = true;

                final ContentValues cv = new ContentValues();

                if(TextUtils.isEmpty(UserName)){
                    userName.setError("Enter User Name");
                    checkMyThings = false;
                }
                if(TextUtils.isEmpty(FirstName)){
                    firstName.setError("Enter First Name");
                    checkMyThings = false;
                }
                if(TextUtils.isEmpty(LastName)){
                    lastName.setError("Enter Last Name");
                    checkMyThings = false;
                }
                if(TextUtils.isEmpty(Email)){
                    email.setError("Enter Email Address");
                    checkMyThings = false;
                }
                if(TextUtils.isEmpty(StudentNumber)){
                    studentNumber.setError("Enter Student Number");
                    checkMyThings = false;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Enter Password");
                    checkMyThings = false;
                }
                Log.d("BeforeCheckTingz","BeforeCheckTings");

                if(checkMyThings == true){
                    /*
                    cv.put("studentNo",StudentNumber);
                    // Log.d("afterPut","afterPut");

                    cv.put("first_name",FirstName);
                    cv.put("last_name",LastName);
                    cv.put("username",UserName);
                    cv.put("password",Password);
                    cv.put("email",Email);


                    register(cv,RegisterActivity.this);
                    //Log.d("afterPut","afterPut");
                    */
                    new RequestHandler(getApplicationContext(),"register").execute(UserName,Password,Email,FirstName,LastName,StudentNumber);
                }
                else{
                    Log.d("InElseForT","InElseForT");
                    Toast toast = Toast.makeText(getApplicationContext(),"Please fill in all required fields",Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
    }

    private static void register(ContentValues cv, final Context c){
        new AsyncHTTP("http://lamp.ms.wits.ac.za/~s1819369/registration.php",cv){


            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String output) {
                Log.d("afterLink","afterLink");
                if(output!=null && output.equals("Registered Successfully")){
                    Toast t = Toast.makeText(c,"You can now Log In",Toast.LENGTH_SHORT);
                    t.show();
                    c.startActivity(new Intent(c,HomeActivity.class));
                }

                else{
                    Toast t = Toast.makeText(c,"Something Went Wrong",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }.execute();
    }
}
