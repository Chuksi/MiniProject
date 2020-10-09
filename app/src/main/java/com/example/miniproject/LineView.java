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
import android.icu.number.Scale;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Range;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.awt.font.NumericShaper;
import java.util.Timer;
import java.util.TimerTask;

public class LineView extends View {
    private Paint paint = new Paint();

    private PointF pointA,pointB;

    Bitmap bitmap;
    //BitmapFactory bitmapFactory;

    public Rect rectA;
    public Rect rectB;

    public int x = this.getWidth();
    public int y = this.getWidth();
    private float i;

    Timer innerTimer = new Timer();

    public LineView(Context context) {
        super(context);
        this.setWillNotDraw(false);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        this.setWillNotDraw(false);

    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(bitmap!=null)
            return;

        //map(canvas.getWidth(), canvas.getHeight());
        map(400,300);
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
        /*innerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 10);*/
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
        this.bitmap = null;
        //z = Math.random()*Math.PI*2;

        z+=Math.PI/1500;

        CX = 0.7885*Math.cos(z);
        CY = 0.7885*Math.sin(z);

        //CX = CX;
        //CY = CY;
        invalidate();
        requestLayout();
    }
    double z = 1.4;
    double v,w;
    private double CX = -0.8;
    private double CY = 0.156;
    double j=0;

    public void map(int w, int h) {
        //int w = 800;
        //int h = 600;
        double aa,bb;
        //double CX = -0.7;
        //double CY = 0.27015;
        double ZOOM = 1;
        double MOVE_X = 0;
        double MOVE_y = 0;
        int MAX_ITERATIONS=30;
        int R = 2 ; // choose R > 0 such that R**2 - R >= sqrt(cx**2 + cy**2)
        bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        rectA = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        rectB = new Rect(0,0,bitmap.getWidth()*3*2,bitmap.getHeight()*3);


        for (int x = 0; x <bitmap.getWidth(); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {

                double zx = 1.5*(x-bitmap.getWidth()/2)/(0.5*ZOOM*bitmap.getWidth())+MOVE_X;
                double zy = (y-bitmap.getHeight()/2)/(0.5*ZOOM*bitmap.getHeight())+MOVE_y;
                //double x0 = Px*(2/bitmap.getWidth());
                //double y0 = Py*(2/bitmap.getHeight());
                //double x = 0.0;
                //double y = 0.0;
                //double temp;
                //int iteration=0;
                float i = MAX_ITERATIONS;
                while(zx*zx + zy*zy< 4 && i >0) {
                    double tmp = zx * zx -zy*zy + CX;
                    zy = 2.0*zx* zy + CY;
                    zx = tmp;
                    i--;
                }
                j = zx*zx + zy*zy;
                int r;
                int g;
                int b;
                //int c = i>0?0:250;
                if(i>0) {
                    r=0;
                    g=0;
                    b=0;
                    //c=(int) (250/i);
                } else {
                    r=(int)(Math.cos(j)*250);
                    g=(int)(Math.sin(j+Math.PI/3)*250);
                    b=(int)(Math.cos(j+Math.PI/6)*250);
                }
                //int a = i>MAX_ITERATIONS? 0:250;
                Range range = new Range(0,255);
                //if()
                range.getLower();
                bitmap.setPixel(x,y,Color.rgb(g, r, b));
                /*if(iteration==maxIteration) {

                    bitmap.setPixel(Px,Py,Color.RED);
                }
                else {
                    bitmap.setPixel(Px,Py,Color.YELLOW);
                }*/
            }
        }

    }
}
