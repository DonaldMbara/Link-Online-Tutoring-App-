package com.example.link_online_tutoring_app_;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

public class RegisterActivityTest {

    public ActivityTestRule<RegisterActivity> activityActivtyTestRule = new ActivityTestRule<>(RegisterActivity.class);


    @Test
    public void onCreate() {
    }

    @Test
    public void onClickRegister() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Button register = activityActivtyTestRule.getActivity().findViewById(R.id.RegisterButton);


                    register.performClick();


                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}

