package org.techtown.registtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment1,container,false);
        if(getArguments()!=null)
        {
            TextView userName = (TextView)v.findViewById(R.id.userNameText);
             String UserName = getArguments().getString("UserName"); //전달한 UserName 값

            userName.setText(UserName);
        }

        mListView = (ListView)v.findViewById(R.id.listView1);
        final View header = getLayoutInflater().inflate(R.layout.listview_header,null,false);

        mListView.addHeaderView(header);

        dataSetting();

        return v;
    }

    //친구 수만큼 리스트 생성
    private void dataSetting(){

        MyAdapter myAdapter = new MyAdapter();

        for(int i=0; i<10; ++i){
            myAdapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.icon),"name"+i,"contents_"+i);
        }

        mListView.setAdapter(myAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



}
