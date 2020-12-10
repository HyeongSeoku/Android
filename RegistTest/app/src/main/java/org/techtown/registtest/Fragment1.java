package org.techtown.registtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

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

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
