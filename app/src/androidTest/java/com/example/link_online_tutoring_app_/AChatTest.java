package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AChatTest {
    @Test
    public void TestingChatPass(){
        Intent intent = new Intent();
         ActivityTestRule rule = new ActivityTestRule(ChatActivity.class, true, false);
         rule.launchActivity(intent);
        ChatActivity.YellowLight = "OFF";
//        onView(withId(R.id.messageEText)).perform(typeText("Some Answer"), closeSoftKeyboard());
        onView(withId(R.id.btnSend)).perform(click());
        onView(withId(R.id.btnReload)).perform(click());

    }
}
