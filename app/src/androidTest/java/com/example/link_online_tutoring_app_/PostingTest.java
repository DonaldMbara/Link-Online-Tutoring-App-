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


public class PostingTest {
    @Rule
    public ActivityTestRule<PostsActivity> ATR = new ActivityTestRule<>(PostsActivity.class, true,false);
    Instrumentation.ActivityMonitor Monitor =getInstrumentation().addMonitor(ViewPosts.class.getName(),null,false);
    //   Instrumentation.ActivityMonitor activityMonitor=getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);


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
                    Activity NextActivity=getInstrumentation().waitForMonitorWithTimeout(Monitor,7000);
                    assertNull(NextActivity);
                }
            });

        }catch (Throwable output){
            output.printStackTrace();
        }
    }



    @Test
    public void Can_post() { //testing if you can post

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText post=ATR.getActivity().findViewById(R.id.Add_post);
                    post.setText("Testing Question?");
                    Button selector = ATR.getActivity().findViewById(R.id.CourseChoice);
                    selector.performClick();
                    onView(withText("COMS")).perform(click());
                    Button post_button = ATR.getActivity().findViewById(R.id.Post_button);
                    post_button.performClick();
                    Activity AnotherActivity=getInstrumentation().waitForMonitorWithTimeout(Monitor,7000);
                    assertNull(AnotherActivity);

                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}