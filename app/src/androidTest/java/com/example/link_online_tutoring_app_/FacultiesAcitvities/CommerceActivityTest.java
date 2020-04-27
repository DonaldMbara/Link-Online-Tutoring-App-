package com.example.link_online_tutoring_app_.FacultiesAcitvities;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.rule.ActivityTestRule;

import com.example.link_online_tutoring_app_.HomeActivity;
import com.example.link_online_tutoring_app_.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;

public class CommerceActivityTest {

    @Rule
    public ActivityTestRule rule=new ActivityTestRule(CommerceActivity.class,true,false);

   // ListView listView = rule.getActivity().findViewById(R.id.listView);


    @Before
    public void setUp() throws Exception {
        //runs before the test

    }
    @Test
    public void onCreate() {
    }


  //  for(int i =0; i < )
    @Test
    public void clickOne(){
        rule.launchActivity(new Intent());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());


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
}