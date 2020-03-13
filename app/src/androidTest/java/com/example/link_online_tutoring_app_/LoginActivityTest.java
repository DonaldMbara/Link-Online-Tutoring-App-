package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);



    Instrumentation.ActivityMonitor activityMonitor=getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        //runs before the test

    }

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