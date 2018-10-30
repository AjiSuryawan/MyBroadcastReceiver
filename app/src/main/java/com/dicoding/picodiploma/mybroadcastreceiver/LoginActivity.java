package com.dicoding.picodiploma.mybroadcastreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edusername;
    EditText edpassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edusername=(EditText)findViewById(R.id.edUsername);
        edpassword=(EditText)findViewById(R.id.edPaswword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here
                if (edusername.getText().toString().equals("admin") &&
                        edpassword.getText().toString().equals("admin") ){

                    Intent in=new Intent(getApplicationContext(),Calculator.class);
                    startActivity(in);

                }
            }
        });

    }
}
