package com.example.link_online_tutoring_app_;


import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class RegisterTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(RegisterActivity.class, true, false);

    @Test
    public void shouldRenderView() throws Exception{
         rule.launchActivity(new Intent());
        onView(withId(R.id.userNameTxt)).inRoot(isPlatformPopup()).check(matches(withHint("Username")));
        onView(withId(R.id.fNameTxt)).inRoot(isPlatformPopup()).check(matches(withHint("First Name")));
        onView(withId(R.id.lNameTxt)).inRoot(isPlatformPopup()).check(matches(withHint("Last Name")));
        onView(withId(R.id.emailTxt)).inRoot(isPlatformPopup()).check(matches(withHint("Email")));
        onView(withId(R.id.stdNumTxt)).inRoot(isPlatformPopup()).check(matches(withHint("Student Number")));
        onView(withId(R.id.passTxt)).inRoot(isPlatformPopup()).check(matches(withHint("Password")));
        onView(withId(R.id.comfirmBtn)).inRoot(isPlatformPopup()).check(matches(withText("REGISTER")));

    }


}
