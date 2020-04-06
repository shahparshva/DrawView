package com.example.parshva.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stevenyang.snowfalling.SnowFlakesLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SnowFlakesLayout snowFlakesLayout;
        snowFlakesLayout = (SnowFlakesLayout) this.findViewById(R.id.snowflakelayout);
        snowFlakesLayout.init();
        snowFlakesLayout.setWholeAnimateTiming(3000000);
        snowFlakesLayout.setAnimateDuration(10000);
        snowFlakesLayout.setGenerateSnowTiming(300);
        snowFlakesLayout.setRandomSnowSizeRange(40, 1);
        snowFlakesLayout.setImageResourceID(R.drawable.snowflake);
        snowFlakesLayout.setEnableRandomCurving(true);
        snowFlakesLayout.setEnableAlphaFade(true);
        snowFlakesLayout.startSnowing();
        snowFlakesLayout.startSnowing();
        snowFlakesLayout.startSnowing();

    }
}
