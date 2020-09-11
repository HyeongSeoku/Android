package com.example.androidpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Mission08_login extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mission08_login);

        final EditText InputUsername08 = (EditText)findViewById(R.id.usernameInput08);
        final EditText InputPassword08 = (EditText)findViewById(R.id.passwordInput08);

        Button loginButton08 = (Button)findViewById(R.id.loginButton08);
        loginButton08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = InputUsername08.getText().toString();
                String passWord = InputPassword08.getText().toString();

                Intent intent = new Intent(getApplicationContext(),Mission08_mainMenu.class);
                intent.putExtra("username",userName);
                intent.putExtra("password",passWord);
                startActivityForResult(intent,REQUEST_CODE_MENU);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);

        if(requestCode == REQUEST_CODE_MENU)
        {
            if(intent != null)
            {
                String menu = intent.getStringExtra("menu");
                String message = intent.getStringExtra("message");

                Toast toast = Toast.makeText(getBaseContext(),"result code:"+resultCode+"menu"+menu+"message"+message,Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
