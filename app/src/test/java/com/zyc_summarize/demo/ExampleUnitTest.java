package com.zyc_summarize.demo;

import android.app.Activity;

import com.zyc_summarize.demo.view.activity.MainActivity;
import com.zyc_summarize.demo.view.activity.SplashActivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addition_isCorrect1() {
        MainActivity mainActivity = new MainActivity();
        List<Activity> mActivityList = new ArrayList<>();
        SplashActivity splashActivity = new SplashActivity();
        mActivityList.add(splashActivity);
        try {
            mActivityList.remove(mainActivity);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}