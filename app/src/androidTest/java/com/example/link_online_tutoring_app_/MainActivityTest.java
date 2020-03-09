package com.example.link_online_tutoring_app_;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Test;
import org.w3c.dom.Text;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class MainActivityTest {

    public ActivityTestRule<MainActivity> activityActivtyTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onCreate() {
    }

    @Test
    public void onClickRegisterHere() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    EditText register = activityActivtyTestRule.getActivity().findViewById(R.id.RegisterButton);
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

                    Button login = activityActivtyTestRule.getActivity().findViewById(R.id.loginBtn);
                    login.performClick();


                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}