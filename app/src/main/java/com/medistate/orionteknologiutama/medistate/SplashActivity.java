package com.medistate.orionteknologiutama.medistate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private boolean InternetCheck=true;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //progressBar
        spinner = findViewById(R.id.progresBar1);
        spinner.setVisibility(View.VISIBLE);
        PostDelayedMethod();
    }

    private void PostDelayedMethod() {
        new Handler().postDelayed(new Runnable() {


            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity

                boolean InternetResult = checkConnection();
                if(InternetResult){
                    finish();
                    //open Activity when internet is connected
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    //     intent.addCategory(Intent.CATEGORY_HOME);
                    //    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                else {
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);


                    //Dialog Box show when internet is not connected
                    DialogAppear();


                }
            }
        }, SPLASH_TIME_OUT);
    }

    public void DialogAppear()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SplashActivity.this);

        builder.setTitle("Network Error");   //Title
        builder.setMessage("No Internet Connectivity");   //Message


        //Negative Message
        builder.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        /* close this activity
                         *  When Exit is clicked
                         */
                        finish();

                    }
                });

        //Positive Message
        builder.setPositiveButton("Retry",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                        //Check internet again when click on Retry by calling function

                        //run is not working there due to runnable method
                        // run();
                        finish();
                        PostDelayedMethod();

                    }
                });
        builder.show();
    }

    //Check Internet status of the mobile
    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    //Return Internet Status of the Mobile
    public boolean checkConnection(){
        if(isOnline()){
            return InternetCheck;
            //Toast.makeText(MainActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
        }else{
            InternetCheck=false;
            return InternetCheck;
            // Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();

        }

    }
}
