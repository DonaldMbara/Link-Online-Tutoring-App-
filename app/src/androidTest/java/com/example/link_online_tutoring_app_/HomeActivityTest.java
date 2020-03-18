package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(HomeActivity.class,true,false);

    @Test
    public void clickOne(){
        rule.launchActivity(new Intent());
       // onView(withId(R.id.listView)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

    }

    @Test
    public void clickTwo(){
        rule.launchActivity(new Intent());
        // onView(withId(R.id.listView)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());

    }

    @Test
    public void clickThree(){
        rule.launchActivity(new Intent());
        // onView(withId(R.id.listView)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());

    }

    @Test
    public void clickFour(){
        rule.launchActivity(new Intent());
        // onView(withId(R.id.listView)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(3).perform(click());

    }
}