package com.yourfav;

import android.graphics.Bitmap;


import java.io.Serializable;

import static android.graphics.Bitmap.createBitmap;

public class Picture implements Serializable {

    //private ImageView image;
    private String url;
    private int id ;

    public Picture(/*ImageView image*/String url,int id) {

        //this.image=image;
        this.url=url;
        this.id=id;
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

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}

