package com.example.clayshootinggame2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_gun;
    ImageView iv_bullet;
    ImageView iv_clay;

    double screen_width, screen_height;
    float bullet_height, bullet_width;
    float gun_height, gun_width;
    float clay_height, clay_width;
    float bullet_center_x, bullet_center_y;
    float clay_center_x, clay_center_y;
    double gun_x, gun_y;
    double gun_center_x;

    final int NO_OF_CLAYS = 5;  // 클레이 개수
    // -------------------------------- 추가 --------------------------------
    TextView tv_status;
    int hit = 0;
    int no_of_clays_left = NO_OF_CLAYS;
    int no_of_hits = 0;
    // ----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);

        // ---------- 화면크기 ----------
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screen_width = size.x;  // 화면 가로크기
        screen_height = size.y; // 화면 세로크기

        // ---------- 애니메이션 이미지 인식 ----------
        iv_bullet = new ImageView(this);
        iv_gun = new ImageView(this);
        iv_clay = new ImageView(this);
        // -------------------------------- 추가 --------------------------------
        tv_status = new TextView(this);
        tv_status.setX(50f);
        tv_status.setY((float)screen_height - 400f);
        tv_status.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        tv_status.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv_status.setTextColor(Color.parseColor("#FFFFFF"));
        tv_status.setTextSize(16);
        layout.addView(tv_status);
        // ----------------------------------------------------------------------

        iv_gun.setImageResource(R.drawable.gun);
        iv_gun.measure(iv_gun.getMeasuredWidth(), iv_gun.getMeasuredHeight());

        gun_height = iv_gun.getMeasuredHeight();
        gun_width = iv_gun.getMeasuredWidth();
        layout.addView(iv_gun);

        iv_bullet.setImageResource(R.drawable.bullet);
        iv_bullet.measure(iv_bullet.getMeasuredWidth(), iv_bullet.getMeasuredHeight());
        bullet_height = iv_bullet.getMeasuredHeight();
        bullet_width = iv_bullet.getMeasuredWidth();
        iv_bullet.setVisibility(View.INVISIBLE);
        layout.addView(iv_bullet);

        iv_clay.setImageResource(R.drawable.clay);
        iv_clay.setScaleX(0.8f);
        iv_clay.setScaleY(0.8f);
        iv_clay.measure(iv_bullet.getMeasuredWidth(), iv_bullet.getMeasuredHeight());
        clay_height = iv_clay.getMeasuredHeight();
        clay_width = iv_clay.getMeasuredWidth();
        iv_clay.setVisibility(View.INVISIBLE);  // Step 2
        layout.addView(iv_clay);

        gun_center_x = screen_width * 0.7;
        gun_x = gun_center_x - gun_width * 0.5;
        gun_y = screen_height - gun_height;
        iv_gun.setX((float) gun_x);
        iv_gun.setY((float) gun_y - 100f);

        ObjectAnimator clay_translateX = ObjectAnimator.ofFloat(iv_clay, "translationX", -200f, (float) screen_width + 100f);
        ObjectAnimator clay_translateY = ObjectAnimator.ofFloat(iv_clay, "translationY", 50f, 50f);
        ObjectAnimator clay_rotation = ObjectAnimator.ofFloat(iv_clay, "rotation", 0f, 1080f);
        clay_translateX.setRepeatCount(NO_OF_CLAYS - 1);
        clay_translateY.setRepeatCount(NO_OF_CLAYS - 1);
        clay_rotation.setRepeatCount(NO_OF_CLAYS - 1);
        clay_translateX.setDuration(3000);
        clay_translateY.setDuration(3000);
        clay_rotation.setDuration(3000);

        // Animator - 애니메이션의 시작, 종료, 등등 클래스
        // Animator.AnimatorListener - 애니메이션 시작부터 종료까지의 과정에 대한 정보를 받음
        clay_translateX.addListener(new Animator.AnimatorListener() {

            // 애니메이션 시작 시 실행
            @Override
            public void onAnimationStart(Animator animator) {
                iv_clay.setVisibility(View.VISIBLE);
                tv_status.setText("게임 시작" + "\n남은 클레이 수:" + no_of_clays_left + " / 5\n명중한 수 : + no_of_hits");
            }

            // 애니메이션 종료 시 실행
            @Override
            public void onAnimationEnd(Animator animator) {
                // -------------------------------- 추가 --------------------------------
                if(hit == 1)
                    no_of_hits ++;
                no_of_clays_left --;
                tv_status.setText("게임 종료!!" + "\n남은 클레이 수: " + no_of_clays_left + " / 5\n명중한 수:" + no_of_hits);
                // ----------------------------------------------------------------------
                // Toast.makeText(getApplicationContext(), "게임 종료!!", Toast.LENGTH_LONG).show();
            }

            // 애니메이션 취소 시 실행q
            @Override
            public void onAnimationCancel(Animator animator) {

            }

            // 애니메이션이 반복될 때 실행
            @Override
            public void onAnimationRepeat(Animator animator) {
                // -------------------------------- 추가 --------------------------------
                if(hit == 1)
                    no_of_hits ++;
                hit = 0;
                no_of_clays_left --;
                if(no_of_clays_left > 0){
                    iv_clay.setVisibility(View.VISIBLE);
                    tv_status.setText("게임 중" + "\n남은 클레이 수:" + no_of_clays_left + " / 5\n명중한 수: " + no_of_hits);
                    // ---------------------------------------------------------------------
                }
            }
        });

        clay_translateX.start();
        clay_translateY.start();
        clay_rotation.start();
        iv_gun.setClickable(true);
        iv_gun.setOnClickListener(this);

    }
    // 뷰가 클릭될 때 불려짐
    @Override
    public void onClick(View v){
        iv_bullet.setVisibility(View.VISIBLE);
        ObjectAnimator bullet_scaleDownX = ObjectAnimator.ofFloat(iv_bullet, "scaleX", 1.0f, 0.0f);
        ObjectAnimator bullet_scaleDownY = ObjectAnimator.ofFloat(iv_bullet, "scaleY", 1.0f, 0.0f);

        double bullet_x = gun_center_x - bullet_width / 2;
        ObjectAnimator bullet_translateX = ObjectAnimator.ofFloat(iv_bullet, "translationX", (float)bullet_x, (float)bullet_x);
        ObjectAnimator bullet_translateY = ObjectAnimator.ofFloat(iv_bullet, "translationY", (float)gun_y, 30);

        bullet_translateY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bullet_center_x = iv_bullet.getX() + bullet_width * 0.5f;
                bullet_center_y = iv_bullet.getY() + bullet_height * 0.5f;

                clay_center_x = iv_clay.getX() + clay_width * 0.5f;
                clay_center_y = iv_clay.getY() + clay_height * 0.5f;

                double dist = Math.sqrt(Math.pow(bullet_center_x - clay_center_x, 2) + Math.pow(bullet_center_y - clay_center_y, 2));
                if(dist <= 100){
                    iv_clay.setVisibility(View.INVISIBLE);
                    hit=1;
                }
            }
        });

        AnimatorSet bullet = new AnimatorSet();
        bullet.playTogether(bullet_translateX, bullet_translateY, bullet_scaleDownX, bullet_scaleDownY);
        bullet.setDuration(500);
        bullet.start();
    }

}
