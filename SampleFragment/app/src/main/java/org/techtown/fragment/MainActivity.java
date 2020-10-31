package org.techtown.fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();

    }
    public void onFragmentChanged(int index){
        if(index == 0)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
        }else if(index == 1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
        }
    }
}
