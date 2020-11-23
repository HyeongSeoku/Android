package org.techtown.pager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3); //미리 로딩해놓을 아이템 갯수

        MypagerAdapter adapter = new MypagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);
    }

    class MypagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MypagerAdapter(FragmentManager fm){
        super(fm);
        }

        public void  addItem(Fragment item){
        items.add(item);
        }
        @Override
        public Fragment getItem(int position){
            return items.get(position);
        }

        @Override
        public int getCount(){
            return items.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return "페이지"+position ;
        }
    }

}
