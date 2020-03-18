package com.example.link_online_tutoring_app_;


import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class RegisterTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(RegisterActivity.class, true, false);

    @Test
    public void shouldRenderView(){
         rule.launchActivity(new Intent());
       // rule.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        onView(withId(R.id.userNameTxt)).check(matches(withHint("Username")));
        onView(withId(R.id.fNameTxt)).check(matches(withHint("First Name")));
        onView(withId(R.id.lNameTxt)).check(matches(withHint("Last Name")));
        onView(withId(R.id.emailTxt)).check(matches(withHint("Email")));
        onView(withId(R.id.stdNumTxt)).check(matches(withHint("Student Number")));
        onView(withId(R.id.passTxt)).check(matches(withHint("Password")));
        onView(withId(R.id.comfirmBtn)).check(matches(withText("REGISTER")));

    }

    @Test
    public  void register(){
        rule.launchActivity(new Intent());

        onView(withId(R.id.userNameTxt)).perform(typeText("Username"));
        onView(withId(R.id.fNameTxt)).perform(typeText("firstName"));
        onView(withId(R.id.lNameTxt)).perform(typeText("lastname"));
        onView(withId(R.id.emailTxt)).perform(scrollTo()).perform(typeText("email@gmail.com"));
        onView(withId(R.id.stdNumTxt)).perform(scrollTo()).perform(typeText("studentNum"));
        onView(withId(R.id.passTxt)).perform(typeText("password"));
        onView(withId(R.id.comfirmBtn)).perform(scrollTo()).perform(click());
    }

}
