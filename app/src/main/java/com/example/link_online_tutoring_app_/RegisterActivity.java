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

    EditText userName ,firstName,lastName, email, studentNumber,password;
    Button confirmReg;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //final EditText idNum = findViewById(R.id.idNumTxt);
        userName = findViewById(R.id.userNameTxt);
         firstName = findViewById(R.id.fNameTxt);
         lastName = findViewById(R.id.lNameTxt);
         email = findViewById(R.id.emailTxt);
        studentNumber = findViewById(R.id.stdNumTxt);
         password = findViewById(R.id.passTxt);
         confirmReg = findViewById(R.id.button2);


    }

    public void onClickRegister(View view) {

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

            new RequestHandler(getApplicationContext(),"register").execute(UserName,Password,Email,FirstName,LastName,StudentNumber);
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
        else{
            Log.d("InElseForT","InElseForT");
            Toast toast = Toast.makeText(getApplicationContext(),"Please fill in all required fields",Toast.LENGTH_SHORT);
            toast.show();
        }


    }


//    private static void register(ContentValues cv, final Context c){
//        new AsyncHTTP("http://lamp.ms.wits.ac.za/~s1819369/registration.php",cv){
//
//
//            @Override
//            protected void onPreExecute() {
//
//            }
//
//            @Override
//            protected void onPostExecute(String output) {
//                Log.d("afterLink","afterLink");
//                if(output!=null && output.equals("Registered Successfully")){
//                    Toast t = Toast.makeText(c,"You can now Log In",Toast.LENGTH_SHORT);
//                    t.show();
//                    c.startActivity(new Intent(c,HomeActivity.class));
//                }
//
//                else{
//                    Toast t = Toast.makeText(c,"Something Went Wrong",Toast.LENGTH_SHORT);
//                    t.show();
//                }
//            }
//        }.execute();
//    }
}
