package com.yourfav;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vanessa on 28.01.2018.
 */

public class PictureAdapter extends ArrayAdapter<Picture> {

    public PictureAdapter(@NonNull Context context, @NonNull List<Picture> pictures) {
        super(context, 0, pictures);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.picture_item, parent, false);
        }

        PictureViewHolder viewHolder = (PictureViewHolder) convertView.getTag();
        if (viewHolder == null){
            viewHolder = new PictureViewHolder();
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.picture);
            viewHolder.button = (TextView) convertView.findViewById(R.id.button);
            convertView.setTag(viewHolder);
        }

        Picture pictureToDisplay = getItem(position);

        //fill our view
        viewHolder.button.setText("Add to fav");
        //viewHolder.picture.setImageBitmap(pictureToDisplay.getBitmap());
        //viewHolder.picture.setImageResource(R.drawable.yellow_flower);
        Picasso.with(viewHolder.picture.getContext()).load(pictureToDisplay.getUrl()).centerCrop().fit().into(viewHolder.picture);


        return convertView;
    }
}
