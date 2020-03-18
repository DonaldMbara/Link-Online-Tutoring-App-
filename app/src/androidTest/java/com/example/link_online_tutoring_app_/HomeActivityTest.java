package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
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

    @Before
    public void setUp() throws Exception {
        //runs before the test

    }

    @Test
    public void onCreate() {
    }

    @Test
    public void clickOne(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        //onView(withId(R.id.listView)).perform(closeSoftKeyboard());


    }

    @Test
    public void onCreate1() {
    }

    @Test
    public void clickTwo(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());
      //  onView(withId(R.id.listView)).perform(closeSoftKeyboard());

    }

    @Test
    public void onCreate2() {
    }

    @Test
    public void clickThree(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());
      //  onView(withId(R.id.listView)).perform(closeSoftKeyboard());

    }

    @Test
    public void onCreate4() {
    }

    @Test
    public void clickFour(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(3).perform(click());
       // onView(withId(R.id.listView)).perform(closeSoftKeyboard());

    }
    @Test
    public void onCreate5() {
    }
    @Test
    public void navigation(){
        rule.launchActivity(new Intent());
        onData(withId(R.id.bottom_navigation)).onChildView(withId(R.id.private_chat)).perform(click());
    }


}