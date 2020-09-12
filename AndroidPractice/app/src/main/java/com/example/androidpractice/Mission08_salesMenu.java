package com.example.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Mission08_salesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mission08_submenu02);
        Button backToMenu = (Button)findViewById(R.id.buttonBacktoMenu);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("meesage","result message is OK!");

                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });

        Button backToLogin = (Button)findViewById(R.id.buttonBacktoLogin);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mission08_login.class);
                startActivity(intent);
            }
        });
    }
}
