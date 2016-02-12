package com.example.taher.localarea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Login extends Activity {

    private Button signup, login, forget;
    private EditText _uname, _upassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _uname = (EditText) findViewById(R.id.username_signup);
        _upassword = (EditText) findViewById(R.id.password_signup);

        signup = (Button) findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Signup.class);
                startActivityForResult(intent, 0);
            }
        });

        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_uname.length() == 0 ||_upassword.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Enter username and password"
                                    , Toast.LENGTH_LONG).show();
                }
                else
                {

                    Background process = new Background(getApplicationContext());

                    process.execute("login", _uname.getText().toString(),
                            _upassword.getText().toString());

                    try {
                        process.get();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }



                    if (!process.result.equals("-1")) {
                        //Toast.makeText(getApplicationContext(), process.result + "login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), Home.class);
                        intent.putExtra("user_id", process.result);
                        startActivityForResult(intent, 0);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong username or password...", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        forget = (Button) findViewById(R.id.forgetButton);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //soon ^_^
            }
        });
    }

}
