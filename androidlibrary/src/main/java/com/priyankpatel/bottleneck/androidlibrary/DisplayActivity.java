package com.priyankpatel.bottleneck.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String joke=getIntent().getStringExtra("joke");
        TextView joke_display=(TextView)findViewById(R.id.display_text);
        assert joke_display != null;
        joke_display.setText(joke);
    }

}
