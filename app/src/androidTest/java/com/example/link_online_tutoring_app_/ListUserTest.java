package com.example.link_online_tutoring_app_;

import android.content.Intent;
import android.widget.LinearLayout;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class ListUserTest {
    @Test
    public void ShowList(){
        ActivityScenario<ListAllUsers> sn = ActivityScenario.launch(ListAllUsers.class);
        onView(withId(R.id.ls)).check(matches(isDisplayed()));
        onView(withId(R.id.WelcomeHomeTv)).check(matches(isDisplayed()));
        onView(withId(R.id.listUsers)).check(matches(isDisplayed()));
    }

    @Test
    public void clickUser(){
        ActivityScenario<ListAllUsers> active = ActivityScenario.launch(ListAllUsers.class);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
    }
}
