package com.evelyn.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //Evelyn Ling's code
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!OpenCVLoader.initDebug())
        {
            Log.d("evelyn","OpenCv Fail");
        }
        else{
            Log.d("evelyn","OpenCv Success");
        }
        Button btn = findViewById(R.id.button);
        try {
            btn.setOnClickListener(new View.OnClickListener() {
                Mat image = Utils.loadResource(MainActivity.this,R.drawable.appleicon,CvType.CV_8UC4);
                @Override
                public void onClick(View v) {
                    try {
                        image = drawALine(image, image.width(), image.height());
                        Bitmap bitmap = Bitmap.createBitmap(image.width(), image.height(), Bitmap.Config.ARGB_8888);
                        Utils.matToBitmap(image,bitmap);
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e){
                        Log.d("evelyn",""+e);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Mat drawALine(Mat i, int w, int h)
    {
        Scalar lineColor = new Scalar(255,0,0,255);
        Point startingP = null;
        Point endingP = null;
        final int lineThickness = 10;
        while(count <= 4) {
            if (count == 1) {
                startingP = new Point(w / 4, h / 4);
                endingP = new Point(w / 4, h / 4 * 3);
            }
            if (count == 2) {
                startingP = new Point(w / 4, h / 4 * 3);
                endingP = new Point(w / 4 * 3, h / 4 * 3);
            }
            if (count == 3) {
                startingP = new Point(w / 4 * 3, h / 4 * 3);
                endingP = new Point(w/ 4 * 3, h /4);
            }
            if (count == 4) {
                startingP = new Point(w / 4 * 3, h /4);
                endingP = new Point(w /4, h /4);
            }
            Imgproc.line(i, startingP, endingP, lineColor, lineThickness);
            count++;
            return i;
        }
        return i;
    }
}