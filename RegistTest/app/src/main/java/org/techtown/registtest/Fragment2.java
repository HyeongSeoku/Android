package org.techtown.registtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
//채팅방

public class Fragment2 extends Fragment {
    private ListView chatListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2,container,false);
        setHasOptionsMenu(true);    //액션바
        chatListView = (ListView)v.findViewById(R.id.listView2);
        chatDataSetting();
        return v;
    }
    //채팅방 수만큼 리스트 생성
    private void chatDataSetting(){

        ChatAdapter myAdapter = new ChatAdapter();

        for(int i=0; i<15; ++i){
            myAdapter.addItem(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.icon),"name"+i,"contents_"+i);
        }

        chatListView.setAdapter(myAdapter);
    }


}

/*202.12.23 추가해야할 기능
1. 채팅목록이 없을경우 "대화 가능한 채팅방이 없습니다" 문구 들어간 화면 띄우기
2. 친구 목록에서 친구 클릭후 채팅버튼 클릭시 대화 창으로 연결 시키고 , 인텐트로 친구 이름 , 사진 가져오기
*/




