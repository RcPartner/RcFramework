package com.rc.framework.imageloader;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-22 11:35
 */
public interface ImageLoaderInterface {

    void loadImage(ImageView imageView, Uri uri);

    void loadImage(ImageView imageView, String uri);
}
