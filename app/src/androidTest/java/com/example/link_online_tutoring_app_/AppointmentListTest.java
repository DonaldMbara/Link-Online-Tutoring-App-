package com.example.link_online_tutoring_app_;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.link_online_tutoring_app_.AptListUser.Apt_ListUsers;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AppointmentListTest {
    @Test
    public void ShowList(){
        ActivityScenario<Apt_ListUsers> s = ActivityScenario.launch(Apt_ListUsers.class);
        onView(withId(R.id.apt_linlayid)).check(matches(isDisplayed()));
    }
    @Test
    public void ZPressBack(){
        ActivityScenario<Apt_ListUsers> s = ActivityScenario.launch(Apt_ListUsers.class);
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.homeid)).check(matches(isDisplayed()));
    }
}
