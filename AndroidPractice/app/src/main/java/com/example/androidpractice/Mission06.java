package com.example.androidpractice;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class Mission06 extends AppCompatActivity {
    ProgressBar progressBar ;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission06);

        progressBar  = findViewById(R.id.progressbar);
        editText = findViewById(R.id.editText);

        SeekBar seekBar  =findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                editText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
