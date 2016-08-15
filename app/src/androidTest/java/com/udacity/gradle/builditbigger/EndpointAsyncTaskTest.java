package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by priyankpatel on 8/15/16.
 */
public class EndpointAsyncTaskTest extends AndroidTestCase implements OnJokeReceivedListener {
    EndpointAsyncTask endpointAsyncTask;
    String joke="";
    CountDownLatch signal;

    OnJokeReceivedListener onJokeReceivedListener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);

        endpointAsyncTask = new EndpointAsyncTask();
    }

    @UiThreadTest
    public void testDownload() throws InterruptedException
    {
        endpointAsyncTask.execute(onJokeReceivedListener);
        signal.await(30, TimeUnit.SECONDS);

        assertTrue("Valid joke is returned", joke !=null);
    }

    @Override

    public void onReceived(String j) {
        joke = j;
        signal.countDown();
    }
}
