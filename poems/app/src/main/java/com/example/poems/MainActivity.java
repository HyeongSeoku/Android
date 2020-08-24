package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("시 목록");
    }
    public void displayPoem(View v){
        int id = v.getId();
        LinearLayout layout = (LinearLayout) v.findViewById(id);
        String tag = (String) layout.getTag();

        Intent it = new Intent(this,Poem.class);//메인 액티비티(this) 에서 Poem 클래스로
        it.putExtra("it_tag",tag); // tag 값을 받는쪽에서 "it_tag"값으로 받음
        startActivity(it);
    }
}
