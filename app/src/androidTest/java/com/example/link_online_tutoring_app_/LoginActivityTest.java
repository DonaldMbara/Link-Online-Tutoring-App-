package com.example.link_online_tutoring_app_;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

public class LoginActivityTest {

    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void onCreate() {
    }

    @Test
    public void onClickRegisterHere() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    EditText register = activityTestRule.getActivity().findViewById(R.id.RegisterButton);
                    register.performClick();


                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



    @Test
    public void onClickLogin() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Button login = activityTestRule.getActivity().findViewById(R.id.loginBtn);
                    login.performClick();


                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}