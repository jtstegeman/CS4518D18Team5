package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kyle on 1/30/18.
 */

public class SuspectFaceDetector {

    private FaceDetector mFaceDetector;

    public SuspectFaceDetector(Context context){
        mFaceDetector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();
    }

    /**
     * Detects faces in an image.
     * @param image The image to find faces in.
     * @return A list of the faces in the image.
     */
    public List<Face> detectFaces(Bitmap image){
        List<Face> goodFaces = new LinkedList<>();
        if (mFaceDetector.isOperational()) {
            Frame frame = new Frame.Builder().setBitmap(image).build();
            SparseArray<Face> faces = mFaceDetector.detect(frame);
            for (int i = 0; i < faces.size(); i++) {
                Face face = faces.valueAt(i);
                goodFaces.add(face);
            }
        }
        return goodFaces;
    }

    /**
     * Releases the detector.
     */
    public void release(){
        mFaceDetector.release();
    }

}
