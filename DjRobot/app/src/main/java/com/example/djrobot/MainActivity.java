package com.example.djrobot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {

        ConstraintLayout cl;
        ImageView iv;
        private TextToSpeech tts;
        Intent intent;
        SpeechRecognizer mRecognizer;
        MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl = (ConstraintLayout)findViewById(R.id.cl);
        iv = (ImageView)findViewById(R.id.iv);

        mp = new MediaPlayer();

        //TTS 객체 생성
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        // 앱 처음 실행시, 음성인식 허용 여부 확인 요청
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }
        //음성인식을 위한 인텐트 생성
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");

        //음성인식 객체 생성과 리스너 생성
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);

        //볼륨 조절
        AudioManager am =(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
        am.setStreamVolume(am.STREAM_MUSIC,amStreamMusicMaxVol,0);
    }

    //음성 인식 리스너 객체 생성
    private RecognitionListener recognitionListener = new RecognitionListener() {

        //사용자가 말할 때 까지 대기하는 상태
        @Override
        public void onReadyForSpeech(Bundle bundle) {

        }

        //사용자가 말하기 시작할 때
        @Override
        public void onBeginningOfSpeech() {

        }

        //음성의 사운드 수준이 변할 때
        @Override
        public void onRmsChanged(float v) {

        }

        //여러 소리가 들려질 때
        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        //사용자가 말을 마칠 때
        @Override
        public void onEndOfSpeech() {

        }

        //네트워크 또는 음성인식 에러
        @Override
        public void onError(int i) {

        }

        //음성인식 결과
        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> mResult = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            if (rs[0].equals("학교종")) {
                iv.setImageResource(R.drawable.bell);
                mp = MediaPlayer.create(MainActivity.this, R.raw.elephant);

                mp.start();
            } else if (rs[0].equals("산토끼")) {
                iv.setImageResource(R.drawable.rabbit);
                mp = MediaPlayer.create(MainActivity.this, R.raw.rabbit);
            } else if (rs[0].equals("코끼리 아저씨")) {
                iv.setImageResource(R.drawable.elephant);
                mp = MediaPlayer.create(MainActivity.this, R.raw.elephant);

                mp.start();
            }
        }

        //음성인식이 부분적으로 된 경우
        @Override
        public void onPartialResults(Bundle bundle) {

        }

        //발생 예정 이벤트 설정
        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };

    //화면이 터치될 때 실행되는 콜백메소드
    public void recognizeVoice(View v){
        if(mp.isPlaying()){
            mp.stop();
            mp.release();
        }

        tts.speak("어떤 노래를 들려드릴까요",TextToSpeech.QUEUE_FLUSH,null);
        mRecognizer.startListening(intent);
    }

    //앱이 종료될 때 실행
    @Override
    public void onDestroy(){
        super.onDestroy();

        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }

        if(mp.isPlaying()){
            mp.stop();
            mp.release();
        }
    }
}
