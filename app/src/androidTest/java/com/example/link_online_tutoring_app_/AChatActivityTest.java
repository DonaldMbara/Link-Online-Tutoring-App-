package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class AChatActivityTest {


    @Test
    public void ClickMessagePass() {
        ActivityScenario<ChatActivity> sn = ActivityScenario.launch(ChatActivity.class);
        onView(withId(R.id.chat_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.receiverName)).check(matches(isDisplayed()));
        onView(withId(R.id.btnReload)).check(matches(isDisplayed()));
        onView(withId(R.id.ThirdCL)).check(matches(isDisplayed()));
        onView(withId(R.id.messageEText)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSend)).check(matches(isDisplayed()));
        onView(withId(R.id.messageEText)).perform(typeText("To ME"), closeSoftKeyboard());
        onView(withId(R.id.btnSend)).perform(click());
        onView(withId(R.id.btnReload)).perform(click());

    }

    @Test
    public void ClickMessageFail() {
        ActivityScenario<ChatActivity> sn = ActivityScenario.launch(ChatActivity.class);
        onView(withId(R.id.chat_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.receiverName)).check(matches(isDisplayed()));
        onView(withId(R.id.btnReload)).check(matches(isDisplayed()));
        onView(withId(R.id.ThirdCL)).check(matches(isDisplayed()));
        onView(withId(R.id.messageEText)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSend)).check(matches(isDisplayed()));
        onView(withId(R.id.messageEText)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnSend)).perform(click());
    }

}