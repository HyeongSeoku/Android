package com.example.androidpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Mission08_mainMenu extends AppCompatActivity {
    public static final int REQUEST_CODE_CUSTOMER = 201;
    public static final int REQUEST_CODE_SALES = 202;
    public static final int REQUEST_CODE_PRODUCTS = 203;
    @Override

    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission08_mainmenu);

        Intent receivedIntent = getIntent();
        String username = receivedIntent.getStringExtra("username");
        String password = receivedIntent.getStringExtra("password");

        Toast.makeText(this,"username : "+username+", password : "+password,Toast.LENGTH_LONG).show();

        Button backButton = (Button)findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("message", "result message is OK!");

                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }
        });

        Button menu01Button = (Button)findViewById(R.id.buttonCustomer);
        menu01Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mission08_customerMenu.class);
                        intent.putExtra("titleMsg","고객 관리 화면");

                startActivityForResult(intent,REQUEST_CODE_CUSTOMER);
            }
        });
        Button menu02Button = (Button)findViewById(R.id.buttonSales);
        menu02Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mission08_salesMenu.class);
                intent.putExtra("titleMsg","매출 관리 화면");

                startActivityForResult(intent,REQUEST_CODE_SALES);
            }
        });
        Button menu03Button = (Button)findViewById(R.id.buttonProducts);
        menu03Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mission08_productMenu.class);
                intent.putExtra("titleMsg","상품 관리 화면");

                startActivityForResult(intent,REQUEST_CODE_PRODUCTS);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode , Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);

        if(intent != null){
            if(requestCode == REQUEST_CODE_CUSTOMER)
            {
                String message = intent.getStringExtra("message");

                if(message != null){
                    Toast toast = Toast.makeText(getBaseContext(),"고객관리 응답, result code : "+resultCode+",message : "+message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }else if(requestCode == REQUEST_CODE_SALES){
                String message = intent.getStringExtra("message");

                if(message != null){
                    Toast toast = Toast.makeText(getBaseContext(),"매출관리 응답, result code : "+resultCode+",message : "+message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }else if(requestCode == REQUEST_CODE_PRODUCTS){
                String message = intent.getStringExtra("message");

                if(message != null){
                    Toast toast = Toast.makeText(getBaseContext(),"상품관리 응답, result code : "+resultCode+",message : "+message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        }
    }
}
