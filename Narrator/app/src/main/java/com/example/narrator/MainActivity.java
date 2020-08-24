package com.example.narrator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout cl;
    TextView tv;
    TextView ttv;
    TextView ptv;
    private TextToSpeech tts;
    Intent intent;
    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl = (ConstraintLayout)findViewById(R.id.cl);
        tv = (TextView)findViewById(R.id.tv);

        //TTS 객체 생성
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        //앱 처음 실행시, 음성인식 허용 여부 확인 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
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
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle bundle) {

            ArrayList<String> mResult = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            if (rs[0].equals("별 헤는 밤")) {
               //ttv.setText(R.string.title1);
                //ptv.setText(R.string.poet1);
                tv.setText(R.string.body1);
                tts.speak(getText(R.string.body1).toString(),TextToSpeech.QUEUE_FLUSH,null);
            }else if(rs[0].equals("내가 너를 사랑하고 있는지는")){
                //ttv.setText(R.string.title2);
                //ptv.setText(R.string.poet2);
                tv.setText(R.string.body2);
                tts.speak(getText(R.string.body2).toString(),TextToSpeech.QUEUE_FLUSH,null);
            }else if(rs[0].equals("종료")){
                tv.setText("앱 종료");
                tts.speak("종료하겠습니다",TextToSpeech.QUEUE_FLUSH,null);
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            else
            {
                tv.setText("해당 시가 존재하지 않습니다");
            }
        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };
    //화면이 터치될 때 실행되는 콜백메소드
    public void recognizeVoice(View v){
        if(tts.isSpeaking()){
            tts.stop();
        }
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

        if(tts.isSpeaking()){
            tts.stop();
        }
    }
}
