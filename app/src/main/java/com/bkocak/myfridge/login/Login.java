package com.bkocak.myfridge.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bkocak.myfridge.R;

/**
 * Created by burakcankocak on 18/05/2017.
 */

public class Login extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

}
