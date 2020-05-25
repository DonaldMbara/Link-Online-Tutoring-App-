package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.ScienceActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

public class scienceTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(ScienceActivity.class, true, false);


    @Test
    public void onCreate7() {
    }

    @Test
    public void clickseven(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.private_chat)).perform(click());

    }



    @Test
    public void onCreate8() {
    }

    @Test
    public void clickEight(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.posting)).perform(click());

    }


    @Test
    public void onCreate9() {
    }

    @Test
    public void clickNine(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.action_faculties)).perform(click());

    }

/*
    @Test
    public void onCreate10() {
    }


    @Test
    public  void click1(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());


    }


    @Test
    public void onCreate11() {
    }


    @Test
    public  void click2(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());


    }




    @Test
    public void onCreate12() {
    }


    @Test
    public  void click3(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).perform(click());


    }


    @Test
    public void onCreate13() {
    }


    @Test
    public  void click4(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(3).perform(click());


    }

*/

}

