package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.util.List;

public class PictureUtils {
    public static Bitmap getScaledBitmap(String path, Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay()
                .getSize(size);

        return getScaledBitmap(path, size.x, size.y);
    }

    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {
        // read in the dimensions of the image on disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(path, options);
    }

    public static Canvas outlineFaces(Bitmap bitmap, Context context) {
        SuspectFaceDetector faceDector = new SuspectFaceDetector(context);
        List <Face> faces  =  faceDector.detectFaces(bitmap);

        Canvas canvas = new Canvas(bitmap);

        for(Face face: faces){
            float height = face.getHeight();
            float width = face.getWidth();
            Float centerx = face.getPosition().x;
            Float centery = face.getPosition().y;

            int left = Math.round(centerx - width/2);

//            canvas.drawRect(new Rect());

        }
        return canvas;
    }
}
