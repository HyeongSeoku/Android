package com.example.projectweek7;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("동영상 목록");
    }
    public void displayVideo(View v){
        int id = v.getId();
        LinearLayout layout = (LinearLayout) v.findViewById(id);
        String tag = (String) layout.getTag();

        Intent it = new Intent(this, Video.class);
        it.putExtra("it_tag",tag);
        startActivity(it);
    }
}
