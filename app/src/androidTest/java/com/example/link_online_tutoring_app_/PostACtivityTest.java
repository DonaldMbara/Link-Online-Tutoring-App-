package com.example.link_online_tutoring_app_;




import android.content.Intent;

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
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)


public class PostACtivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(PostsActivity.class, true, false);


    @Test
    public void onCreate1() {
    }

    @Test
    public void shouldRenderView() {
        rule.launchActivity(new Intent());
        onView(withId(R.id.PostYourQ)).check(matches(withText("Write and post your questions")));
        onView(withId(R.id.Add_post)).check(matches(withHint("Write your question...")));
        onView(withId(R.id.Post_button)).check(matches(withText("Post")));
        onView(withId(R.id.CourseChoice)).check(matches(withText("Course")));
     //   onView(withId(R.id.Viewer)).check(matches(withText("")));
    }

    @Test
    public void post(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.Add_post)).perform(typeText("this is the testing question!?!!"));
        onView(withId(R.id.Add_post)).perform(closeSoftKeyboard());
        //onView(withId(R.id.CourseChoice)).perform(click());
        //onView(withId(R.id.CourseChoice)).perform(closeSoftKeyboard());
        onView(withId(R.id.Post_button)).perform(click());

    }


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




}
