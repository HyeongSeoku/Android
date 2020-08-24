package com.example.project9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},MODE_PRIVATE);  //마시멜로우버전 이후 꼭 들어가야함. 퍼미션 지정(기능)  *시험문제 가능성*
        setContentView(R.layout.activity_main);
    }

    public void call(View v){
        int id = v.getId(); //지정한 id 값을 가져옴
        LinearLayout layout = (LinearLayout) v.findViewById(id); //레이아웃의 id 값과 연결시켜줌
        String tel = (String) layout.getTag();

        Uri number = Uri.parse("tel:"+tel); //
        Intent intent = new Intent (Intent.ACTION_CALL, number);
        startActivity(intent);
    }
}