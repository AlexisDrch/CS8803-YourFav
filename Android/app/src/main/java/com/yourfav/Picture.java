package com.yourfav;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class Picture implements Serializable {

    private ImageView image;
    private String url;

    public Picture(ImageView image) {
        //temporary (until API works)
        this.image=image;
        //this.image.setImageResource(R.drawable.yellow_flower);
        //this.image = image;
    }

    public Drawable getDrawable(){
        return image.getDrawable();
    }

    public ImageView getImView(){
        return image;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public String getUrl(){
            return url;}
}

