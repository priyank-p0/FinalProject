package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.priyankpatel.myapplication.backend.*;
import com.example.priyankpatel.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by priyankpatel on 7/28/16.
 */
public class EndpointsAsyncTask extends AsyncTask<OnJokeReceivedListener, Void, String> {
    private static MyApi myApiService = null;
    private OnJokeReceivedListener listener;

    @Override
    protected String doInBackground(OnJokeReceivedListener... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://localhost:8080");

            myApiService = builder.build();
        }

        listener = params[0];

        try {

            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onReceived(result);
    }
}