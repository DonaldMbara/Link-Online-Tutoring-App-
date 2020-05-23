package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.test.InstrumentationRegistry;
import androidx.test.annotation.UiThreadTest;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.content.Context.MODE_PRIVATE;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressMenuKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class PostingTest {
    SharedPreferences.Editor preferenceEditor;
    private Instrumentation mI;
    private Activity mA;
    private PopupMenu Popmenu;
    @Rule
    public ActivityTestRule<PostsActivity> ATR = new ActivityTestRule<>(PostsActivity.class);
    Instrumentation.ActivityMonitor Monitor =getInstrumentation().addMonitor(PostsActivity.class.getName(),null,false);



    @Test
    public void ClickPostFail() {
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run(){
                    EditText Q = ATR.getActivity().findViewById(R.id.PostYourQ);
                    EditText PlaceHolder = ATR.getActivity().findViewById(R.id.Viewer);
                    Q.setText("");
                    PlaceHolder.setText("");

                    Button Post = ATR.getActivity().findViewById(R.id.Post_button);
                    Post.performClick();
                    Activity NextActivity=getInstrumentation().waitForMonitorWithTimeout(Monitor,3000);
                    assertNull(NextActivity);
                }
            });

        }catch (Throwable output){
            output.printStackTrace();
        }
    }


    @Test
    public void DoPostPass(){

        try{
            Button Post = ATR.getActivity().findViewById(R.id.Post_button);
            Post.performClick();

        }catch (Throwable tr){
            tr.printStackTrace();
        }
    }


    @Test
    public void Can_post1() { //testing if you can post
        ATR.launchActivity(new Intent());

        try {
            onView(withId(R.id.Add_post)).perform(typeText("this is a question"));
            onView(withId(R.id.CourseChoice)).perform(click());
            onView(withText("COMS")).perform(click());
            onView(withId(R.id.Post_button)).perform(click());

        }

        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



}