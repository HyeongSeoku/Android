package org.techtown.registtest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<MyItem> mitems = new ArrayList<>();

    @Override
    public int getCount() {
        return mitems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return mitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
        }

        ImageView iv_img = (ImageView)convertView.findViewById(R.id.FriendProfileImage);
        TextView  tv_name =(TextView)convertView.findViewById(R.id.friendsId);
        TextView  tv_msg =(TextView)convertView.findViewById(R.id.stateMsg);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MyItem myItem = getItem(position);

        iv_img.setImageDrawable(myItem.getIcon());
        tv_name.setText(myItem.getName());
        tv_msg.setText(myItem.getContents());

        return convertView;
    }

    public void addItem(Drawable img , String name , String contents){

        MyItem mitem = new MyItem();

        mitem.setIcon(img);
        mitem.setName(name);
        mitem.setContents(contents);

        mitems.add(mitem);
    }
}
