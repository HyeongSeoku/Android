package org.techtown.registtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;

    private BottomNavigationView mBottomNV;
    Toolbar myToolbar;
    private String UserName;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activty_main);

        //LoginActivity에서 넘어온 User값 잠시 저장
        Intent getintent = getIntent();
        UserName = getintent.getStringExtra("UserName");

        //툴바 지정
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mBottomNV = findViewById(R.id.BottomnavigationView);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.userList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "검색 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_friend_add:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "친구추가 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_music:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "음악 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "환경설정 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }


    }
    private void BottomNavigate(int id){

        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();

        if(currentFragment != null){
            fragmentTransaction.hide(currentFragment);
        }
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if(fragment == null){
            if(id == R.id.userList){
                fragment = new Fragment1();

                Bundle bundle = new Bundle();
                bundle.putString("UserName",UserName);
                fragment.setArguments(bundle);

            }else  if(id == R.id.chatting){
                fragment = new Fragment2();
            }else  if(id == R.id.hashtag){
                fragment = new Fragment3();
            }else  if(id == R.id.dotdot){
                fragment = new Fragment4();
            }
            fragmentTransaction.add(R.id.content_layout,fragment,tag);
        }else{
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

}
