/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.example.bannerexample;
//import start
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
//import end


/**
 * @author Jacob Anderson
 * @version 1.0
 */
public class MyActivity extends ActionBarActivity
{
//declaration
    private Button numberUp;//declares the button numberUp
    private TextView clickAmount;//declares the textview clickAmount
    private AdView bannerAd;//declares the banner ad bannerAd
    private long clicks;//declares the long(any whole number) clicks
    private Button adGone;// declares the button adGone
    private boolean adGoneToggle;// declares the boolean(true/false)adGoneToggle
//end of declaration
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        clicks = 0;// the value of clicks, which is 0
        adGoneToggle = false;// the value of adGoneToogle, which is false

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        bannerAd = (AdView) findViewById(R.id.ad_view);//initializes the bannerAd ad view, so it can be used in the program
        numberUp =(Button) findViewById(R.id.numberUp);//initialize the button numberUp, so it can be used in the program
        clickAmount =(TextView) findViewById(R.id.clickAmount);//initialize the textview clickAmount, so it can be used in the program
        adGone =(Button) findViewById(R.id.adGone);//initialize the button aGone, so it can be used in the program

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        bannerAd.loadAd(adRequest);

        setupListeners();
        isAdGoneGood();
    }

    //Makes an action happen when a button is clicked.
    private void setupListeners()
    {   //When the button "numberUp" is clicked it does an action.
        numberUp.setOnClickListener(new View.OnClickListener()
        {
           @Override
           //The public void onClick contains the action that the button should execute.
                   public void onClick(View currentView)
            {
                //When numberUp is clicked the variable clicks goes up by one.
                clicks = clicks+1;
                //"clickAmount.setText" refreshes the TextView clickAmount to show the number of clicks.
                clickAmount.setText("Clicks:"+clicks);
                isAdGoneGood();

            }


        });
        //Makes an action happen when a button is clicked.
        adGone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            //When the button "adGone" is clicked it does an action.
        public void onClick(View currentView)
            {
                //if clicks is greater than or equal to 100 and adGoneToggle is false it executes whats in the {}
                if(clicks >= 100 && adGoneToggle == false)
                {
                    bannerAd.setVisibility(View.INVISIBLE);//makes the bannerAd not visible
                    adGoneToggle = true;//makes adGoneToggle true
                }

                //if clicks is greater than or equal to 100, adGoneToggle is true and the if then block above failed it executes whats in the {}
                if(clicks >= 100 && adGoneToggle == true)
                {
                    bannerAd.setVisibility(View.VISIBLE);//makes the bannerAD visible
                    adGoneToggle = false;//makes adGoneToggle false
                }
            }
        });




        ////////////////

        }
    private void isAdGoneGood()
    {
        if(clicks >= 100 )
        {
            adGone.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause()
    {
        if (bannerAd != null)
        {
            bannerAd.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume()
    {
        super.onResume();
        if (bannerAd != null)
        {
            bannerAd.resume();
        }
        /** Called before the activity is destroyed */
        @Override
        public void onDestroy() 
        {
        if (bannerAd != null) {
            bannerAd.destroy();
        }
        super.onDestroy();
    }




    }


}
