package com.bkocak.myfridge.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bkocak.myfridge.R;

import com.bkocak.myfridge.config.Config;
import com.bkocak.myfridge.main.MyFridgeApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

/**
 * Created by burakcankocak on 18/05/2017.
 */

public class Login extends Activity {
    //Objects
    Config config;
    //Visual components
    Button button_register,button_login, button_facebookLogin,button_passwordRest;
    EditText editText_email, editText_password;
    CheckBox cbRememberMe;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        config = new Config(Login.this);

        mAuth = FirebaseAuth.getInstance();
        setContentComponents();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if(firebaseAuth.getCurrentUser() != null)
               {
                   //TODO : Open app here .
               }
            }
        };
    }

    private void setContentComponents() {
        button_login = (Button) findViewById(R.id.bLogin);
        button_register = (Button) findViewById(R.id.bRegister);
        button_facebookLogin = (Button) findViewById(R.id.bFacebookLogin);
        button_passwordRest = (Button) findViewById(R.id.bPasswordReset);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signIn();
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        button_facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Login using facebook account must be processed here.
            }
        });

        button_passwordRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               passwordReset();
            }
        });

        editText_email = (EditText) findViewById(R.id.etEmail);
        editText_email.setText(config.getEMail().toString());
        editText_password = (EditText) findViewById(R.id.etPassword);

        cbRememberMe = (CheckBox) findViewById(R.id.cbRememberMe);
        if(config.getEMail().toString() != "")
        {
            cbRememberMe.setChecked(true);
        }

    }

    private void signIn() {
        String eMail = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        if(TextUtils.isEmpty(eMail) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Please fill all fields !", Toast.LENGTH_SHORT).show();

        }else{
            mAuth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "Sign in problem occured", Toast.LENGTH_SHORT).show();
                    }else{
                        if(cbRememberMe.isChecked())
                        {
                            //TODO : SAVE USERNAME HERE.
                            config.setEMail(editText_email.getText().toString());
                        }
                        Toast.makeText(Login.this, "User logged in !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MyFridgeApp.class));
                    }
                }
            });

        }

    }

    private void registerUser(){
        String eMail = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        if(TextUtils.isEmpty(eMail) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Please fill all fields !", Toast.LENGTH_SHORT).show();

        }else{
            mAuth.createUserWithEmailAndPassword(eMail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "Problem occured", Toast.LENGTH_SHORT).show();
                    }else{
                        if(cbRememberMe.isChecked())
                        {
                            config.setEMail(editText_email.getText().toString());
                        }
                        Toast.makeText(Login.this, "Account created succesfully !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void passwordReset(){
        String eMail = editText_email.getText().toString();
        if(TextUtils.isEmpty(eMail) || !eMail.contains("@")){
            //eMail Field is empty or doesnt contain '@'
            Toast.makeText(Login.this, "Please make sure that you entered valid e-Mail", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.sendPasswordResetEmail(editText_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Login.this, "This e-Mail is not registered yet", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this, "Password reset mail sent to related e-Mail address", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
