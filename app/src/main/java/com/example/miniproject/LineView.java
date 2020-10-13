package com.example.miniproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.icu.number.Scale;
import android.os.Build;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Range;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.awt.font.NumericShaper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LineView extends View {
    private static final String TAG = "wdafw";
    private Paint paint = new Paint();
    public static final int REQUEST_CAMERA = 1;
    private PointF pointA,pointB;

    Bitmap bitmap;
    //BitmapFactory bitmapFactory;
    public int niter;
    public Rect rectA;
    public Rect rectB;

    //public int x = this.getWidth();
    //public int y = this.getWidth();
    //private float i;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    //@Override
    protected void onDraw(Canvas canvas) {
        if(bitmap!=null)
            return;

        //map(canvas.getWidth(), canvas.getHeight());
         map(getWidth() / 3, getHeight() / 3);
         //map(getWidth(), getHeight());

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
                double t;
                int b,g,r;
                //int r = i>0?0:125;
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

                    //c=(int) (250/i);

                } else {
                    //r=(float)(Math.abs(Math.cos(j))*(Math.abs(Math.cos(j))));
                    //g=(float)(Math.abs(Math.cos(j+Math.PI/2))*Math.abs(Math.cos(j+Math.PI/2)));
                    //b=(int)((Math.abs(Math.cos(j+Math.PI)))*250);
                    //b=60f;
                    //r=100+(int)(0.95+10*smoothcolor);
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
    /*
    String a="/root/sdcard/Pictures/img0001.jpg";
    public void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }
    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getContext().getPackageName()
                + "/Files");
        Toast msg = Toast.makeText(getContext(),mediaStorageDir+"",Toast.LENGTH_LONG);
        msg.show();
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

     */

}
