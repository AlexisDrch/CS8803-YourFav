package com.yourfav;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class Picture implements Serializable {

    private ImageView image;

    public Picture(/*ImageView image*/) {
        //temporary (until API works)
        this.image.setImageResource(R.drawable.yellow_flower);
        //this.image = image;
    }

    public Drawable getDrawable(){
        return image.getDrawable();
    }
    /* private String imageUrl;

    public Picture(String url) {
        imageUrl=url;
    }

    public String getUrl(){
        return imageUrl;
    }*/
}

