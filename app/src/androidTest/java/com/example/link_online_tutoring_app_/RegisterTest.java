package com.example.link_online_tutoring_app_;


import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class RegisterTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(RegisterActivity.class, true, false);

    @Test
    public void shouldRenderView() throws Exception {
         rule.launchActivity(new Intent());
        onView(withId(R.id.userNameTxt)).check(matches(withText("Username")));
        onView(withId(R.id.fNameTxt)).check(matches(withText("First Name")));
        onView(withId(R.id.lNameTxt)).check(matches(withText("Last Name")));
        onView(withId(R.id.emailTxt)).check(matches(withText("Email")));
        onView(withId(R.id.stdNumTxt)).check(matches(withText("Student Number")));
        onView(withId(R.id.passTxt)).check(matches(withText("Password")));
        onView(withId(R.id.comfirmBtn)).check(matches(withText("REGISTER")));

    }


}
