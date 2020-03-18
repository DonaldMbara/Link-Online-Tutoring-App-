package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(HomeActivity.class,true,false);

    @Test
    public void clickOne(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.listView)).perform(closeSoftKeyboard());


    }

    @Test
    public void clickTwo(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());

    }

    @Test
    public void clickThree(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());
        onView(withId(R.id.listView)).perform(closeSoftKeyboard());

    }

    @Test
    public void clickFour(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(3).perform(click());
        onView(withId(R.id.listView)).perform(closeSoftKeyboard());

    }

    @Test
    public void navigation(){
        rule.launchActivity(new Intent());
        onData(withId(R.id.bottom_navigation)).onChildView(withId(R.id.private_chat)).perform(click());
    }


}