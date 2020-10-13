package com.example.miniproject;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.DragEvent;
import android.view.PixelCopy;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "permission";
    PointF pointA = new PointF(23, 400);

    PointF pointB = new PointF(410, 1000);

    public boolean animation = false;
    int mpause=1;
    int pause=1;

    float mkoef;
    int miter;


    private LineView mLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mLineView = findViewById(R.id.lineView);
        Button wikiButton = findViewById(R.id.button);
        Button saveButton = findViewById(R.id.button2);
        Switch demo = findViewById(R.id.switch1);
        SeekBar reim = findViewById(R.id.seekBar);
        SeekBar resolution = findViewById(R.id.seekBar2);
        //isStoragePermissionGranted();
        mLineView.setPointA(pointA);

        mLineView.setPointB(pointB);
/*
        mLineView.setOnClickListener(v ->{
            if(pause==1){
                mpause=1;
                pause = 0;
            } else {
                mpause=0;
                pause=1;
            }
        });

 */

        reim.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mkoef = (float)(progress-500)/1500;
                mLineView.draw(2,mkoef,miter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        resolution.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                miter = (int)progress;
                mLineView.draw(2,mkoef,miter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Timer myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(animation) {

                    mLineView.post(() -> {
                        mLineView.draw(mpause,mkoef,12);
                    });
                }
            };
        }, 1000, 150);
        // mLineView.draw();

        //mLineView.map();


        wikiButton.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("https://en.wikipedia.org/wiki/Julia_set"));
            startActivity(sendIntent);
        });

        saveButton.setOnClickListener(v -> {
            FileOutputStream fos = null;
            File file = getDisc();
            if (!file.exists() && !file.mkdirs()) {
                Toast.makeText(this, "Can't create directory to store image", Toast.LENGTH_LONG).show();
                return;
                //print("file not created");
                //return;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
            String date = simpleDateFormat.format(new Date());
            String name = "FileName" + date + ".jpg";
            String file_name = file.getAbsolutePath() + "/" + name;
            File new_file = new File(file_name);
            //print("new_file created");
            Log.d(TAG,"new_file");
            try {
                fos = new FileOutputStream(new_file);
                Bitmap bitmap = viewToBitmap(mLineView, mLineView.getWidth(), mLineView.getHeight());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                Toast.makeText(this, "Save success", Toast.LENGTH_LONG).show();
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                //print("FNF");
                Log.d(TAG,"FNF");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            refreshGallery(new_file);
        });

        demo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    animation = true;
                    reim.setEnabled(false);
                    resolution.setEnabled(false);

                } else {
                    //do nothing
                    animation = false;
                    mLineView.draw(2,mkoef,miter);
                    reim.setEnabled(true);
                    resolution.setEnabled(true);

                }
            }
        });


    }

/*
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    mLineView.storeImage(mLineView.bitmap);
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
 */

    public void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    private File getDisc() {
        String t = getCurrentDateAndTime();
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(file, "ImageDemo");
    }

    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static Bitmap viewToBitmap(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}