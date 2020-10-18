package com.example.middleexam;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class PersonnelInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 레이아웃 내의 뷰 인식
        TextView tv_name   = (TextView) findViewById(R.id.name);
        TextView tv_gender = (TextView) findViewById(R.id.gender);
        TextView tv_hobby  = (TextView) findViewById(R.id.hobby);
        ImageView iv_photo = (ImageView) findViewById(R.id.photo);

        // 데이터 수신
        Intent it = getIntent();
        String str_name = it.getStringExtra("it_name");
        String str_gender = it.getStringExtra("it_gender");
        String str_hobby1 = it.getStringExtra("it_hobby1");
        String str_hobby2 = it.getStringExtra("it_hobby2");
        String str_hobby3 = it.getStringExtra("it_hobby3");
        String str_photoUri = it.getStringExtra("it_photoUri");
        Toast.makeText(this, "str_hobby1: " + str_hobby1.length(), Toast.LENGTH_SHORT).show();

        // 뷰에 데이터 출력
        String str_hobby = "";
        if (!str_hobby1.equals(""))
            str_hobby = str_hobby + str_hobby1 + "\n";
        if (!str_hobby2.equals(""))
            str_hobby = str_hobby + str_hobby2 + "\n";
        if (!str_hobby3.equals(""))
            str_hobby = str_hobby + str_hobby3 + "\n";

        tv_name.setText(str_name);
        tv_gender.setText(str_gender);
        tv_hobby.setText(str_hobby);

        Uri photoUri = Uri.parse(str_photoUri);
        iv_photo.setImageURI(photoUri);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personnel_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // 메뉴 클릭 시, 해당 액티비티로 이동
        if (id == R.id.action_settings) {
            /////////////////////////////////////////////////////////////////
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
            /////////////////////////////////////////////////////////////////
            return true;
        } else if (id == R.id.action_settings2) {
            Intent it = new Intent(this, PersonnelReg.class);
            startActivity(it);
            finish();
            return true;
        }
        ////////////////////////////////////////////////

        return super.onOptionsItemSelected(item);
    }
}
