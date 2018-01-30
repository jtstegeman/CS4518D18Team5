package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CrimeImgGalleryActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";
    private static final String EXTRA_FACE_DETECT =
            "com.bignerdranch.android.criminalintent.face_detect";

    public static Intent newIntent(Context packageContext, UUID crimeId, Boolean faceDetect) {
        Intent intent = new Intent(packageContext, CrimeImgGalleryActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(EXTRA_FACE_DETECT, faceDetect);
        return intent;
    }

    private Boolean faceDetectEnabled;
    private SuspectFaceDetector suspectFaceDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_gallery);

        suspectFaceDetector = new SuspectFaceDetector(this);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        faceDetectEnabled = getIntent().getBooleanExtra(EXTRA_FACE_DETECT, false);

        CrimeLab lab = CrimeLab.get(this);

        final List<File> imgs = lab.getPhotoFiles(crimeId.toString());

        GridView gal = (GridView) findViewById(R.id.gallery_table);
        gal.setAdapter(new ImageAdapter(imgs, this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        suspectFaceDetector.release();
    }

    private class ImageAdapter extends BaseAdapter {

        final List<File> imgs;
        final Activity ctx;

        public ImageAdapter(List<File> imgs, Activity context) {
            this.imgs = imgs;
            ctx = context;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0x2000 + i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView v = new ImageView(ctx);
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    imgs.get(i).getPath(), ctx);
            if (faceDetectEnabled) bitmap = suspectFaceDetector.boxFaces(bitmap);
            v.setImageBitmap(bitmap);
            v.setAdjustViewBounds(true);
            return v;
        }
    }
}
