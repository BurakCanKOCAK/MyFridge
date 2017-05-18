package com.bkocak.myfridge.intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bkocak.myfridge.R;

/**
 * Created by burakcankocak on 18/05/2017.
 */

public class intro extends Activity {
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent loginIntent =  new Intent("com.bkocak.myfridge.login.Login");
                    startActivity(loginIntent);
                }
            }
        };
        timerThread.start();

    }

}
