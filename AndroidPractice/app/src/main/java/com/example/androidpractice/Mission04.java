package com.example.androidpractice;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;

public class Mission04 extends AppCompatActivity {
    EditText inputMessage;
    TextView inputCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission04);

        inputMessage = (EditText)findViewById(R.id.inputMessage);
        inputCount   = (TextView)findViewById(R.id.inputCount);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String message = inputMessage.getText().toString();
                Toast.makeText(getApplicationContext(),"전송할 메시지\n\n" + message, Toast.LENGTH_LONG).show();
            }
        });

        Button closeButton = (Button) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }
        });

        TextWatcher watcher = new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
                byte[] bytes = null;
                try {
                    bytes = str.toString().getBytes("KSC5601");
                    int strCount = bytes.length;                        //입력한 메세지의 바이트 수
                    inputCount.setText(strCount + " / 80바이트");      //바이트 수 세는 라인
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable strEditable) {
                String str = strEditable.toString();
                try {
                    byte[] strBytes = str.getBytes("KSC5601");
                    if (strBytes.length > 80) {
                        strEditable.delete(strEditable.length() - 2, strEditable.length() - 1);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
                inputMessage.addTextChangedListener(watcher);
        }
    }

