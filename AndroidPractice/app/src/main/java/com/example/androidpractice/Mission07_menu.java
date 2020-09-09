package com.example.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Mission07_menu extends AppCompatActivity {
        public static final int RESPONSE_CODE_OK = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission07_menu);

        //intent 받는과정
        Intent receivedIntent = getIntent();
        String username = receivedIntent.getStringExtra("username");
        String password = receivedIntent.getStringExtra("password");

        Toast.makeText(this,"username : "+username+"password : "+password,Toast.LENGTH_LONG).show();

        Button menu01Button = (Button)findViewById(R.id.menu01Button);
        menu01Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("menu","고객관리 메뉴");
                resultIntent.putExtra("message","result message is OK!");

                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        Button menu02Button = (Button)findViewById(R.id.menu02Button);
        menu01Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("menu","매출관리 메뉴");
                resultIntent.putExtra("message","result message is OK!");

                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        Button menu03Button = (Button)findViewById(R.id.menu03Button);
        menu01Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("menu","상품관리 메뉴");
                resultIntent.putExtra("message","result message is OK!");

                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });
    }
}
