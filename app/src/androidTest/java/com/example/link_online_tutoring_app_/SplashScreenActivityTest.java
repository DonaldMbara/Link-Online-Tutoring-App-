package com.example.link_online_tutoring_app_;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class SplashScreenActivityTest {

    @Rule
    public  ActivityTestRule<SplashScreenActivity> activityActivtyTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void onCreate() {
    }

    @Test
    public void onClickContinue() {

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Button continuebtn = activityActivtyTestRule.getActivity().findViewById(R.id.ContinueButton);
                    TextView welcomeTxt = activityActivtyTestRule.getActivity().findViewById(R.id.WelcomeTextView);

                    continuebtn.performClick();


                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}