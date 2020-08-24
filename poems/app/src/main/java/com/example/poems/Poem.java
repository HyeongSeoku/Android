package com.example.poems;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Poem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem);

        //LinearLayout || =(LinearLayout) findViewById(R.id.background);

        TextView tv_title  = (TextView)findViewById(R.id.title); // poem.xml 의 title
        TextView tv_author = (TextView)findViewById(R.id.author);
        TextView tv_body   = (TextView)findViewById(R.id.body);

        Intent it = getIntent();
        String tag = it.getStringExtra("it_tag");

        Resources res = getResources();

        int id_title = res.getIdentifier("title"+tag,"string",getPackageName()); //title에 태그 붙임  ex) title + 01
        String title = res.getString(id_title);
        tv_title.setText(title);

        int id_author = res.getIdentifier("author"+tag,"string",getPackageName());
        String author = res.getString(id_author);
        tv_author.setText(author);

        int id_body = res.getIdentifier("body"+tag,"string",getPackageName());
        String body = res.getString(id_body);
        tv_body.setText(body);

    }
    public void closePoem(View v){finish();}
}
