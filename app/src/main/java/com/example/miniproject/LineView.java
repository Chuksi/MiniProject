package com.example.miniproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LineView extends View {
    private static final String TAG = "wdafw";
    private Paint paint = new Paint();

    Bitmap bitmap;
    public int niter;
    public Rect rectA;
    public Rect rectB;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    //@Override
    protected void onDraw(Canvas canvas) {
        if(bitmap!=null)
            return;

         map(getWidth() / 3, getHeight() / 3);

        canvas.drawBitmap(bitmap,rectA,rectB,paint);

        super.onDraw(canvas);
    }

    public void draw(int test, float koeff,int iterater) {
        this.bitmap = null;
        //z = Math.random()*Math.PI*2;
        if(test==1) {
            z += Math.PI / 100;

            CX = 0.7885 * Math.cos(z);
            CY = 0.7885 * Math.sin(z);

        }
        else if(test == 2) {
            CX = koeff;
            CY = koeff;
            niter = iterater;
        }
        //CX = CX;
        //CY = CY;
        invalidate();
        requestLayout();
    }
    double z = 0.7;
    double v,w;
    private double CX = -0.8;
    private double CY = 0.156;
    double j=0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void map(int w, int h) {
        //int w = 800;
        //int h = 600;
        double aa,bb;

        //double CX = -0.7;
        //double CY = 0.27015;
        double ZOOM = 1;
        double MOVE_X = 0;
        double MOVE_y = 0;
        float Saturation = 1f;
        int MAX_ITERATIONS=niter;
        int R = 2 ; // choose R > 0 such that R**2 - R >= sqrt(cx**2 + cy**2)
        bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        rectA = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        rectB = new Rect(0,0,bitmap.getWidth()*3,bitmap.getHeight()*3);


        for (int x = 0; x <bitmap.getWidth(); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {

                double zx = 1.5*(x-bitmap.getWidth()/2)/(0.5*ZOOM*bitmap.getWidth())+MOVE_X;
                double zy = (y-bitmap.getHeight()/2)/(0.5*ZOOM*bitmap.getHeight())+MOVE_y;
                float i = MAX_ITERATIONS;
                while(zx*zx + zy*zy< 4 && i >0) {
                    double tmp = zx * zx -zy*zy + CX;
                    zy = 2.0*zx* zy + CY;
                    zx = tmp;
                    i--;
                }
                j = zx*zx + zy*zy;
                double t;
                int b,g,r;
                /*
                float Brightness = i < MAX_ITERATIONS ? 1f : 0;
                float Hue = (i%256)/255.0f;
                Paint myc = new Paint();
                float[] hsv = {Hue,Saturation,Brightness};
                */
                //double smoothcolor = Math.exp(-Math.abs(j));

                //for(int k=0;k<MAX_ITERATIONS && Math.abs(j) < 1000;k++) {
                  //  j += j;
                    //smoothcolor += Math.exp(-Math.abs(j));
                //}
                //int c = i>0?0:250;

                if(i>0) {
                    b=0;
                    g=0;
                    r=0;

                } else {
                    if (j>1.8) {
                        b=200+(int)i;
                        g=210-(int)i;
                        r=200;
                    }
                    else if(j >1.2) {
                        b=210;
                        g=50;
                        r=0;
                    }
                    else if(j>0.8) {
                        r=230;
                        b=0;
                        g=20;
                    }
                    else if(j>0.5) {
                        r=100;
                        b=250;
                        g=200;
                    }
                    else if(j>0.2) {
                        r=250;
                        b=250;
                        g=250;
                    }
                    else {
                        b = 250-(int)i;
                        g=180+(int)i;
                        r=0;
                    }
                }
                bitmap.setPixel(x,y,Color.rgb(r,g,b));

                //int a = i>MAX_ITERATIONS? 0:250;
                //Range range = new Range(0,255);
                //if()
                //range.getLower();
                //bitmap.setPixel(x,y,Color.HSVToColor(new float[]{b,g,r}));
                //bitmap.setPixel(x,y,Color.rgb(r,r,r));
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
