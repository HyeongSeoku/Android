package com.example.androidpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Mission03 extends AppCompatActivity {
    ImageView imageView01;
    ImageView imageView02;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission03);

        imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView02 = (ImageView) findViewById(R.id.imageView02);

        imageView01.setImageResource(R.drawable.beach);
        Button button01 =(Button) findViewById(R.id.button);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveImageUp();
            }
        });

        Button button02 =(Button) findViewById(R.id.button2);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveImageDown();
            }
        });

    }
    private void moveImageDown(){
        imageView01.setImageResource(0);
        imageView02.setImageResource(R.drawable.beach);

        imageView01.invalidate();
        imageView02.invalidate();
    }
    private void moveImageUp(){
        imageView02.setImageResource(0);
        imageView01.setImageResource(R.drawable.beach);

        imageView01.invalidate();
        imageView02.invalidate();
    }
}
