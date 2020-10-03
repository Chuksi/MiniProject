package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PointF pointA = new PointF(400,300);

    PointF pointB = new PointF(410,1000);

    private LineView mLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLineView = findViewById(R.id.lineView);

        mLineView.setPointA(pointA);

        mLineView.setPointB(pointB);

        mLineView.draw();

        Button press = findViewById(R.id.button);

        press.setOnClickListener(v->{
            pointA.x +=10;
        });


    }
}