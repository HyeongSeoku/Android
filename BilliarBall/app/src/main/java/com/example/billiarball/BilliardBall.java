package com.example.billiarball;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class BilliardBall extends View {
    private ShapeDrawable mDrawable;        //도형 그리는 것

    //당구공 초기값 설정
    int x = 0;
    int y = 0;
    int width = 100;
    int height = 100;

    int cx,cy;      //그래픽 객체의 중심 좌표

    int dir_x = 1;
    int dir_y = 1;

    int dx = 5;
    int dy = 15;

    //화면의 가로와 세로 크기(1000,1000)
    int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screen_height = Resources.getSystem().getDisplayMetrics().heightPixels;


    public BilliardBall(Context context) {      //자바 클래스 생성자
        super(context);     //View 클래스의 생성자로 호출 (View 클래스의 기본 설정을 따르기 위해)

        mDrawable = new ShapeDrawable(new OvalShape());             //타원 그래픽 객체 생성
        mDrawable.getPaint().setColor(Color.RED);                   //그래픽 색 지정


    }
      protected void onDraw(Canvas canvas){
        //그래픽 중심 좌표
        cx = x + width/2;
        cy = y + height/2;

        //x 방향의 전환
        if(cx <= width/2)
            dir_x = 1;
        else if(cx >= screen_width-width/2)
            dir_x = -1;
        //y 방향의 전환
          if(cy <= height/2)
              dir_y = 1;
          else if(cy >= screen_height-height/2)
              dir_y = -1;
        //x,y 방향의 이동
          x+= dir_x *dx;
          y+= dir_y *dy;

          canvas.drawColor(Color.BLUE);       //캔버스 색 지정

          mDrawable.setBounds(x,y,x+width,y+height);  //그래픽 출력 위치
          mDrawable.draw(canvas);           //그래픽 객체를 캔버스에 출력

          invalidate();     //그래픽 객체를 지우고 다시 onDraw 메소드 호출함
      }
}
