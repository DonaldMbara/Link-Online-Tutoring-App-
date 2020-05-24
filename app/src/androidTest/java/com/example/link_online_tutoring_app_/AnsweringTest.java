package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNull;


public class AnsweringTest {


    @Rule
    public ActivityTestRule<AnswerActivity> ActRule = new ActivityTestRule<>(AnswerActivity.class, true,false);
    Instrumentation.ActivityMonitor ActMonitor =getInstrumentation().addMonitor(AnswerActivity.class.getName(),null,false);


    @Test
    public void onClickAnswerFail(){
        ActRule.launchActivity(new Intent());
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText Answer = ActRule.getActivity().findViewById(R.id.Add_Ans);
                    Button SendAnswer = ActRule.getActivity().findViewById(R.id.Answer_button);
                    Answer.setText("");
                    SendAnswer.performClick();
                    Activity NextActivity=getInstrumentation().waitForMonitorWithTimeout(ActMonitor,3000);
                    assertNull(NextActivity);
                }
            });

        }catch (Throwable output){
            output.printStackTrace();
        }
    }

    @Test
    public void onClickAnswerPass(){
        ActRule.launchActivity(new Intent());
        try{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText Answer = ActRule.getActivity().findViewById(R.id.PostYourA);
                    Button SendAnswer = ActRule.getActivity().findViewById(R.id.Answer_button);
                    Answer.setText("Some Testing Answer");
                    SendAnswer.performClick();
                    Activity GotoActivity=getInstrumentation().waitForMonitorWithTimeout(ActMonitor,3000);
                    assertNull(GotoActivity);
                }
            });

        }catch (Throwable out){
            out.printStackTrace();
        }
    }


}
