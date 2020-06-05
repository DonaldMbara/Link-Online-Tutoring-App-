package com.example.link_online_tutoring_app_;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class PostingTest {
    SharedPreferences.Editor PE;
    public static String SHARED_PREF_LOGIN="shared_prefs_login";

    @Test
    public void PostingPass() {
        Context cx = getInstrumentation().getTargetContext();
        PE = cx.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE).edit();
        PE.putString("Key", "90");
        PE.apply();
        PE.commit();

        ActivityScenario<PostsActivity> sn = ActivityScenario.launch(PostsActivity.class);
        onView(withId(R.id.cl)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_post)).check(matches(isDisplayed()));
        onView(withId(R.id.Viewer)).check(matches(isDisplayed()));
        onView(withId(R.id.Post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_post)).perform(typeText("Q"), closeSoftKeyboard());
        onView(withId(R.id.Viewer)).perform(setTextInTextView("COMS"));

        PostsActivity.HoldName = "TestName";
        PostsActivity.Holding = "1";

        onView(withId(R.id.Post_button)).perform(click());


//        onView(withId(R.id.Post_button)).perform(click());
    }
    public static ViewAction setTextInTextView(final String value){
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
//                                            ^^^^^^^^^^^^^^^^^^^
// To check that the found view is TextView or it's subclass like EditText
// so it will work for TextView and it's descendants
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }

    @Test
    public void PostingFail() {
        ActivityScenario<PostsActivity> sn = ActivityScenario.launch(PostsActivity.class);
        onView(withId(R.id.cl)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_post)).check(matches(isDisplayed()));
        onView(withId(R.id.Viewer)).check(matches(isDisplayed()));
        onView(withId(R.id.Post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_post)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.Viewer)).perform(setTextInTextView(""));

        onView(withId(R.id.Post_button)).perform(click());

    }
    @Test
    public void APostingPressAgain() {
        ActivityScenario<PostsActivity> sn = ActivityScenario.launch(PostsActivity.class);
        onView(withId(R.id.cl)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_post)).check(matches(isDisplayed()));
        onView(withId(R.id.Viewer)).check(matches(isDisplayed()));
        onView(withId(R.id.Post_button)).check(matches(isDisplayed()));
        onView(withId(R.id.Add_post)).perform(typeText("Q2"), closeSoftKeyboard());
        onView(withId(R.id.Viewer)).perform(setTextInTextView("COMS"));
        PostsActivity.Holding  = "";
        onView(withId(R.id.Post_button)).perform(click());

    }

    @Test
    public void ZBackPressTest(){
        ActivityScenario<PostsActivity> sn = ActivityScenario.launch(PostsActivity.class);
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.homeid)).check(matches(isDisplayed()));
    }
}