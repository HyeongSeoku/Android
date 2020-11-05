package org.techtown.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ListMainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int [] images = {R.drawable.forest,R.drawable.lake,R.drawable.mountain};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmain);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
    }

    @Override
    public void onImageSelected(int position)
    {
        viewerFragment.setImage(images[position]);
    }

}
