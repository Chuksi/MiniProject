package com.example.miniproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class LineView extends View {
    private Paint paint = new Paint();

    private PointF pointA,pointB;
    int iteration=0;
    int maxIteration=3;
    Bitmap bitmap;
    //BitmapFactory bitmapFactory;

    public Rect rectA;
    public Rect rectB;


    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(bitmap==null)
            return;
        //paint.setColor(Color.RED);
        //paint.setStrokeWidth(1);
        //canvas.drawLine(pointA.x,pointA.y,pointB.x,pointB.y,paint);
        //paint.setColor(Color.BLUE);
        //canvas.drawCircle(pointB.x,pointB.y,100,paint);

        //canvas.drawPoint(pointB.x,pointB.y,paint);
        //for(int i=0;i<500;i+=10) {

            //canvas.drawPoint(pointB.x+i,pointB.y+i,paint);
            //canvas.drawColor(Color.BLACK);
        //int r = 10;
        //canvas.setBitmap(bitmap);
        //for (int i = 0;i<1000;i++) {

          //  canvas.drawPoint(Math.sin((i/100)*),(200+i)^2,paint);

     //   }//canvas.setBitmap(bitmap);
            //canvas.drawColor(Color.BLACK);
        //canvas.drawRect(pointA.x, pointA.y,pointB.x,pointB.y,paint);
        //canvas.drawPoint(pointA.x,pointA.y,paint);
        //int iteration = 0;
        //int maxIteration = 2;
        //int y = canvas.getHeight()/2;
        //int x = canvas.getWidth()/2;
        //int xtemp;
        //int zx = 0;
        //int zy = 0;

        canvas.drawColor(Color.BLUE);
        //canvas.scale(2,2);
        canvas.drawBitmap(bitmap,rectA,rectB,paint);
        /*
        for (int i = 0;i<getWidth()-1; i++) {
            for (int j = 900; j < 1100; j++) {
                paint.setColor(0xFF008800);
                canvas.drawPoint(i,j,paint);
            }
        }*/


        //}
        super.onDraw(canvas);
    }
    public void setPointA(PointF point) {

        pointA = point;
    }

    public void setPointB(PointF point) {

        pointB = point;
    }

    public void draw() {
        invalidate();
        requestLayout();

    }

    public void map() {

        double zx,zy,xtemp;

        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        for (int i = 0;i<bitmap.getWidth(); i++) {
            for (int j = 0; j < 1000; j++) {
                bitmap.setPixel(i, j, Color.RED);
                //zx =i*getScaleX();
                //zy =j*getScaleY();
                /*while(iteration < maxIteration) {
                    xtemp = zx*zx-zy*zy;
                    zy = 2*zy*zx+0.19;
                    zx = xtemp + -0.726;
                    iteration++;
                }*/
            }
        }
        rectA = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        rectB = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    }
}
