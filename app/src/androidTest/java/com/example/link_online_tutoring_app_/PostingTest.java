package com.example.link_online_tutoring_app_;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class PostingTest {
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

//    @UiThreadTest
//    public void dropdown() throws Throwable{
//        if (Popmenu != null){
//            CountDownLatch latch = new CountDownLatch(1);
//            try {
//                ATR.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//
//                    }
//                });
//            }catch (Throwable tr){
//                tr.printStackTrace();
//            }
//        }
//    }
}
