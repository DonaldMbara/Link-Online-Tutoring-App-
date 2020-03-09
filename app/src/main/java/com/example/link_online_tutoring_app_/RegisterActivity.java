package com.example.link_online_tutoring_app_;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import managers.AsyncHTTP;

public class RegisterActivity extends AppCompatActivity {

    EditText userName, firstName, lastName, email, studentNumber, password;
    Button confirmReg;

    String UserName, FirstName, LastName, Email, StudentNumber, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        userName = findViewById(R.id.userNameTxt);
        firstName = findViewById(R.id.fNameTxt);
        lastName = findViewById(R.id.lNameTxt);
        email = findViewById(R.id.emailTxt);
        studentNumber = findViewById(R.id.stdNumTxt);
        password = findViewById(R.id.passTxt);
        confirmReg = findViewById(R.id.button2);

        userName.addTextChangedListener(registerTextWatcher);
        firstName.addTextChangedListener(registerTextWatcher);
        lastName.addTextChangedListener(registerTextWatcher);
        email.addTextChangedListener(registerTextWatcher);
        studentNumber.addTextChangedListener(registerTextWatcher);
        password.addTextChangedListener(registerTextWatcher);


    }

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            UserName = userName.getText().toString().trim();
            FirstName = firstName.getText().toString().trim();
            LastName = lastName.getText().toString().trim();
            Email = email.getText().toString().trim();
            StudentNumber = studentNumber.getText().toString().trim();
            Password = password.getText().toString().trim();
            confirmReg.setEnabled(!FirstName.isEmpty() && !LastName.isEmpty() &&
                    !StudentNumber.isEmpty() && !Password.isEmpty() && !Email.isEmpty()
            );
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void onClickRegister(View view) {
        if (confirmReg.isEnabled()) {

            new RequestHandler(getApplicationContext(), "register").execute(UserName, Password, Email, FirstName, LastName, StudentNumber);
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        } else {
            Log.d("InElseForT", "InElseForT");
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all required fields", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
