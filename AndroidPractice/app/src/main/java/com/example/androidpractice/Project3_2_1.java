package com.example.androidpractice;

import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Project3_2_1 extends AppCompatActivity implements SensorEventListener {

    ImageView iv_pineWheel;
    SensorManager sm ;
    Sensor sensor_linear;
    ObjectAnimator object = new ObjectAnimator();
    double timestemp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.project3_2_1);
        iv_pineWheel = (ImageView)findViewById(R.id.iv_pinwheel);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor_linear = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        Button stopButton = (Button)findViewById(R.id.stopButton3_2_1);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.unregisterListener(Project3_2_1.this,sensor_linear);
            }
        });
        Button startButton = (Button)findViewById(R.id.startButton3_2_1);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.registerListener(Project3_2_1.this,sensor_linear,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        sm.registerListener(this,sensor_linear,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()){
            case Sensor.TYPE_LINEAR_ACCELERATION:

            double dt = (event.timestamp - timestemp)/100000000;

            if(dt>1){
                double magnitude = Math.sqrt(Math.pow(event.values[0],2)+Math.pow(event.values[1],2)+Math.pow(event.values[2],2));

                double degree_start = iv_pineWheel.getRotation();
                double degree_end = degree_start +magnitude * 1000;

                object.cancel();
                object = ObjectAnimator.ofFloat(iv_pineWheel,"rotation",(float)degree_start,(float)degree_end);
                object.setInterpolator(new LinearInterpolator());
                object.setDuration(1000);
                object.start();

                timestemp = event.timestamp;
            }
            break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
