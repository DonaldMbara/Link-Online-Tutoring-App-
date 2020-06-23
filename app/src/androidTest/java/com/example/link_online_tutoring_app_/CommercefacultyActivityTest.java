package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

public class CommercefacultyActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(CommerceActivity.class, true, false);




    @Test
    public void clickseven(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.private_chat)).perform(click());

    }




    @Test
    public void clickEight(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.posting)).perform(click());

    }



    @Test
    public void clickNine(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.action_faculties)).perform(click());

    }




    @Test
    public  void click1(){
        rule.launchActivity(new Intent());
      //  onView(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());


    }


  


    @Test
    public  void click2(){
        rule.launchActivity(new Intent());
        //onView(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).perform(click());


    }

}

