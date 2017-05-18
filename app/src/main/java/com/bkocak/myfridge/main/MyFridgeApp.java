package com.bkocak.myfridge.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bkocak.myfridge.R;

/**
 * Created by burakcankocak on 21/05/2017.
 */

public class MyFridgeApp extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfridgeapp_layout);
    }

    private void initializeComponents()
    {
        //TODO: Initialize components here
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
