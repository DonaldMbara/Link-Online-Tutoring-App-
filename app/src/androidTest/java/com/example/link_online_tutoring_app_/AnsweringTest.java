package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
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
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNull;
@RunWith(AndroidJUnit4.class)
public class AnsweringTest {
@Test
    public void AddAnswerPass(){
    ActivityScenario<AnswerActivity> sn = ActivityScenario.launch(AnswerActivity.class);
    onView(withId(R.id.ac)).check(matches(isDisplayed()));
    onView(withId(R.id.Add_Ans)).check(matches(isDisplayed()));
    onView(withId(R.id.Answer_button)).check(matches(isDisplayed()));
    onView(withId(R.id.Add_Ans)).perform(typeText("Some Answer"), closeSoftKeyboard());
    AnswerActivity.The_Post_Id = "1";
    AnswerActivity.Author ="90";
    onView(withId(R.id.Answer_button)).perform(click());
    }

    @Test
    public void AddAnswerFail(){
        ActivityScenario<AnswerActivity> sn = ActivityScenario.launch(AnswerActivity.class);
        onView(withId(R.id.ac)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_Ans)).check(matches(isDisplayed()));
        onView(withId(R.id.Answer_button)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_Ans)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.Answer_button)).perform(click());
    }

    @Test
    public void AddAnswerPressAgain(){
        ActivityScenario<AnswerActivity> sn = ActivityScenario.launch(AnswerActivity.class);
        onView(withId(R.id.ac)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_Ans)).check(matches(isDisplayed()));
        onView(withId(R.id.Answer_button)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_Ans)).perform(typeText("A"), closeSoftKeyboard());

        AnswerActivity.RedLight = "Neutral";
        AnswerActivity.The_Post_Id = "";

        onView(withId(R.id.Answer_button)).perform(click());
    }

}
