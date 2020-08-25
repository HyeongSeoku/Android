package com.example.voicetotext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    Intent intent;
    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.tv);

        //앱 처음 실행 시, 오디오 기록 허용 여부 확인 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
        //음성인식을 위한 인텐트 생성
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        //음성인식 객체 생성과 리스너 설정
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);
    }

    //버튼 클릭 시 실행되는 콜백 함수
    public void recognizeVoice(View view) {
        button.setText("말하는 중..");
        mRecognizer.startListening(intent);
    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        //사용자가 말할 떄 까지 대기하는 상태
        @Override
        public void onReadyForSpeech(Bundle bundle) {
            button.setText("음성인식 대기중...");
            textView.setText("");
        }

        //사용자가 말하기 시작할 때
        @Override
        public void onBeginningOfSpeech() {
            //음성의 사운드 수준이 변할 때
        }

        //여러 소리가 들릴 때
        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        //사용자가 말을 마칠 때
        @Override
        public void onEndOfSpeech() {
            button.setText("버튼을 누르고 말하세요!");
        }

        //네트워크 또는 음성인식 에러
        @Override
        public void onError(int i) {
            textView.setText("말이 없네요...");
        }

        //음성인식 결과
        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> mResult = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            textView.setText(rs[0]);
        }

        //음성인식이 된경우
        @Override
        public void onPartialResults(Bundle bundle) {

        }

        //발생 예정 이벤트 생성
        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };
}

