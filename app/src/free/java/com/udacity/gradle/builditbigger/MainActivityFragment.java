package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.priyankpatel.bottleneck.androidlibrary.DisplayActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokeReceivedListener {
    private ProgressBar mSpinner;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                startJokeActivity();
                }
        });

        requestNewInterstitial();


        Button button = (Button) root.findViewById(R.id.jokebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                }
        else
                   startJokeActivity();
       // requestNewInterstitial();
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        mSpinner = (ProgressBar) root.findViewById(R.id.progressBar);


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    @Override
    public void onReceived(String joke) {
        mSpinner.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), DisplayActivity.class);
        intent.putExtra("joke",joke);
        startActivity(intent);
    }


    public void startJokeActivity(){
        mSpinner.setVisibility(View.VISIBLE);
        new  EndpointAsyncTask().execute(this);
    }

}
