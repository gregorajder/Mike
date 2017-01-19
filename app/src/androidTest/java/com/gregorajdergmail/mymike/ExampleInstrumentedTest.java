package com.gregorajdergmail.mymike;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.gregorajdergmail.mymike.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.gregorajdergmail.mymike", appContext.getPackageName());
    }
}
