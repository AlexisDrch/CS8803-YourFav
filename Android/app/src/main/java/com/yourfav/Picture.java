package com.yourfav;

import android.graphics.Bitmap;


import java.io.Serializable;

import static android.graphics.Bitmap.createBitmap;

public class Picture implements Serializable {

    //private ImageView image;
    private String url;
    private Bitmap bm ;

    public Picture(/*ImageView image*/String url) {

        //this.image=image;
        this.url=url;
    }

    /* public ImageView getImView(){
        return image;
    }
    */

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public Bitmap getBitmap(){
        return bm;
    }

    public void setBitmap(Bitmap bm){
        this.bm=bm;
    }
}

