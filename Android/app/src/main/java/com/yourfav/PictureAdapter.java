package com.yourfav;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Vanessa on 28.01.2018.
 */

public class PictureAdapter extends ArrayAdapter<Picture> {

    public PictureAdapter(@NonNull Context context, @NonNull List<Picture> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.picture_item, parent, false);

        ImageView image = rootView.findViewById(R.id.my_image_view);

        Picture pictureToDisplay = getItem(position);

        //image.setImageResource(R.drawable.yellow_flower);
      //  image.setImageDrawable(pictureToDisplay.getDrawable());
        /*Picture pictureToDisplay = getItem(position);
        RL url = new URL("http://www.google.com"); //Some instantiated URL object
        URI uri = url.toURI();
        image.setImageURI(pictureToDisplay.getUrl());*/

        return rootView;
    }
}
