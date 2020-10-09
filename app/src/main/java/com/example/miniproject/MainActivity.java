package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    PointF pointA = new PointF(23,400);

    PointF pointB = new PointF(410,1000);

    private LineView mLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLineView = findViewById(R.id.lineView);


        mLineView.setPointA(pointA);

        mLineView.setPointB(pointB);

        boolean animation = true;

        Timer myTimer = new Timer();

        mLineView.setOnClickListener(v -> {
            myTimer.cancel();
        });

       // mLineView.draw();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mLineView.post(() -> {
                    mLineView.draw();
                });
            }
        }, 0, 50);
        //mLineView.map();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}