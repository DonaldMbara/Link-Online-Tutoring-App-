package com.example.link_online_tutoring_app_;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ProfileTest {
    @Test
    public void ShowProfile(){
        ActivityScenario<ProfileActivity> m = ActivityScenario.launch(ProfileActivity.class);
        onView(withId(R.id.relativeLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_my_image)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_username)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_firstname)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_lastname)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_email)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_specializes)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_messages)).check(matches(isDisplayed()));
        onView(withId(R.id.pp_schedule)).check(matches(isDisplayed()));
    }
    @Test
    public void ClickSchedule(){
        ActivityScenario<ProfileActivity> m = ActivityScenario.launch(ProfileActivity.class);
        onView(withId(R.id.pp_schedule)).perform(click());
        onView(withId(R.id.calendarcc)).check(matches(isDisplayed()));

    }
}
