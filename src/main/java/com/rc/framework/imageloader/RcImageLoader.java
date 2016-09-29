package com.rc.framework.imageloader;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-22 11:34
 */
public class RcImageLoader implements ImageLoaderInterface {

    public static RcImageLoader INSTANCE = new RcImageLoader();

    private RcImageLoader() {

    }

    @Override
    public void loadImage(ImageView imageView, Uri uri) {
        if (imageView == null) {
            return;
        }
        Picasso.with(imageView.getContext()).load(uri).into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String uri) {
        Picasso.with(imageView.getContext()).load(uri).into(imageView);
    }
}
