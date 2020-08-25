package com.example.projectweek7;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.TextView;
import android.content.Intent;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        setTitle("동영상 재생");

        Intent it = getIntent();
        String tag = it.getStringExtra("it_tag");

        TextView title =(TextView)findViewById(R.id.title);
        TextView place =(TextView)findViewById(R.id.place);
        VideoView videoview = (VideoView)findViewById(R.id.videoView);

        int stringId;
        String mykey;

        Resources res = getResources();

        stringId = res.getIdentifier("title"+tag,"string",getPackageName()); // stringId 변수 생성, title+ tag 값을 받아옴
        mykey = res.getString(stringId); //mykey 변수 생성, 받아온 stringId값을 설정
        title.setText(mykey);

        stringId = res.getIdentifier("place"+tag,"string",getPackageName());
        mykey = res.getString(stringId);
        place.setText(mykey);

        stringId = res.getIdentifier("video"+tag,"string",getPackageName()); //제목을 가져옴
        mykey = res.getString(stringId);

        int id_video = res.getIdentifier(mykey,"raw",getPackageName());

         Uri uri =Uri.parse ("android.resource://com.example.projectweek7/"+id_video);

         videoview.setVideoURI(uri);
         videoview.start();

       MediaController mediaController = new MediaController(this);
        videoview.setMediaController(mediaController);


    }
    public void closeVideo(View v){
        finish();
    }
}
