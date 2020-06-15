package com.example.link_online_tutoring_app_;

import android.content.Context;
import android.content.Intent;
import android.widget.CalendarView;
import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class CalendarActivityTest {
    @Rule
    public ActivityTestRule rule = new ActivityTestRule(CalendarActvity.class, true, false);

    @Test
    public  void enterDate(){
        rule.launchActivity(new Intent());

    }



}
