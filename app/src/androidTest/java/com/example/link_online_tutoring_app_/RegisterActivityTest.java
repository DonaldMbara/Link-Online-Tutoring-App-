package com.example.link_online_tutoring_app_;


import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNull;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityActivtyTestRule = new ActivityTestRule<>(RegisterActivity.class);
    Instrumentation.ActivityMonitor activityMonitor =getInstrumentation().addMonitor(HomeActivity.class.getName(),null,false);

    @Test
    public void onCreate() {
    }


    @Test
    public void onClickRegister() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Button register = activityActivtyTestRule.getActivity().findViewById(R.id.RegisterButton);
                    register.performClick();
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void tryRegister(){
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText UN = activityActivtyTestRule.getActivity().findViewById(R.id.userNameTxt);
                    UN.setText("");
                    EditText FN = activityActivtyTestRule.getActivity().findViewById(R.id.fNameTxt);
                    FN.setText("");
                    EditText LN = activityActivtyTestRule.getActivity().findViewById(R.id.lNameTxt);
                    LN.setText("");
                    EditText EM = activityActivtyTestRule.getActivity().findViewById(R.id.emailTxt);
                    EM.setText("");
                    EditText SN = activityActivtyTestRule.getActivity().findViewById(R.id.stdNumTxt);
                    SN.setText("");
                    EditText PS = activityActivtyTestRule.getActivity().findViewById(R.id.passTxt);
                    PS.setText("");
                    Button register = activityActivtyTestRule.getActivity().findViewById(R.id.RegisterButton);
                    register.performClick();
                    Activity secondActivity=getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
                    assertNull(secondActivity);

                }
            });
        }catch (Throwable thr){
            thr.printStackTrace();
        }

    }


}

