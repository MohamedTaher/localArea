package com.example.taher.localarea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Signup extends Activity {

    private EditText username, email, password, repassword;
    private Button supmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.username_signup);
        email = (EditText) findViewById(R.id.password_signup);
        password = (EditText) findViewById(R.id.password_signup);
        repassword = (EditText) findViewById(R.id.repassword_signup);

        supmit = (Button) findViewById(R.id.supmitButton);
        supmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.length() == 0 || email.length() == 0
                        || password.length() == 0 || repassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Enter ALL data"
                            , Toast.LENGTH_LONG).show();
                } else {
                    if (!(password.getText().toString().equals(repassword.getText().toString())))
                    {
                        Toast.makeText(getApplicationContext(), "re-password and password are not same"
                                , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Background process = new Background(getApplicationContext());

                        process.execute("signup", username.getText().toString(),
                                password.getText().toString(), email.getText().toString());

                        try {
                            process.get();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        //if the same username
                        //verify email

                        Intent intent = new Intent(v.getContext(), Login.class);
                        startActivityForResult(intent, 0);
                        
                        Toast.makeText(getApplicationContext(), "Done" + process.result
                                , Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

}
