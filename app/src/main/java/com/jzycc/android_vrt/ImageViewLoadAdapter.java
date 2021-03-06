package com.jzycc.android_vrt;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jzycc.android_vrt.vrt.adapter.IVRTImageLoadAdapter;

/**
 * @author : Jzy
 * date   : 18-10-8
 */
public class ImageViewLoadAdapter implements IVRTImageLoadAdapter {

    @Override
    public void setImage(final Context mContext, final ImageView imageView, final String imageUrl) {
        imageView.post(new Runnable() {
            @Override
            public void run() {
                if(imageUrl!=null) {
                    if (imageUrl.equals("HomePageDefaultBg")) {
                        Log.i("jzy111", "run: " + imageUrl);
                        Glide.with(mContext).load(R.drawable.homepagedefaultbg).into(imageView);
                    }
                    else {
                        //Log.i("jzy111", "run: "+imageUrl);
                        Glide.with(mContext).load(imageUrl).into(imageView);
                    }
                }
            }
        });
    }
}
