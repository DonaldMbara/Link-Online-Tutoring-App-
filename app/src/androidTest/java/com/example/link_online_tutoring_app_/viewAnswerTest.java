package com.example.link_online_tutoring_app_;

import android.content.Intent;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.internal.inject.InstrumentationContext;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressMenuKey;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.AnyOf.anyOf;


public class viewAnswerTest{

    String answerId = "74"; //" this is the post id for question  "do you think cgv is cool""

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);

    @Test
    public void viewPost(){ // tests if we redirect to the answer activity
        Intent id = new Intent();
        id.putExtra("post_id",answerId);
        rule.launchActivity(id);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

       onView(withText("Reply")).perform(click());



    }
/*
    @Test
    public void likeAnswer(){ //this clicks the like button
        Intent id = new Intent();
        id.putExtra("post_id",answerId);
        rule.launchActivity(id);
        onView(withId(R.id.post_recycle_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, ViewPostTest.dependent.clickChildViewWithId(R.id.post_like_btn)));

    }

*/

    public static class dependent {
        public  static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }
                @Override
                public String getDescription() {
                    return "Click on item with specified TAG";
                }
                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();

                }
            };
        }

    }



}
