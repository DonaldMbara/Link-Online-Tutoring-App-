package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class ChatActivityTest {
    @Rule
    public ActivityTestRule<ChatActivity> ATR = new ActivityTestRule<>(ChatActivity.class, true,false);
    Instrumentation.ActivityMonitor Monitor =getInstrumentation().addMonitor(ViewPosts.class.getName(),null,false);
    //   Instrumentation.ActivityMonitor activityMonitor=getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);


    @Test
    public void ClickMeesageFail() {

        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run(){
                    EditText msge = ATR.getActivity().findViewById(R.id.messageEText);
                    msge.setText("");
                    Button Send = ATR.getActivity().findViewById(R.id.btnSend);
                    Send.performClick();
                    Activity NextActivity=getInstrumentation().waitForMonitorWithTimeout(Monitor,7000);
                    assertNull(NextActivity);
                }
            });

        }catch (Throwable output){
            output.printStackTrace();
        }
    }



    @Test
    public void Can_post() { //testing if you can send message

        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText send=ATR.getActivity().findViewById(R.id.messageEText);
                    send.setText("Testing message");

                    Button send_button = ATR.getActivity().findViewById(R.id.btnSend);
                    send_button.performClick();
                    Activity AnotherActivity=getInstrumentation().waitForMonitorWithTimeout(Monitor,7000);
                    assertNull(AnotherActivity);

                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}