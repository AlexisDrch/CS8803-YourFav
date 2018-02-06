package com.yourfav;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            viewHolder.button = (Button) convertView.findViewById(R.id.button);
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //get the row the clicked button is in
                    RelativeLayout vwParentRow = (RelativeLayout)v.getParent();
                    ListView lv=(ListView)vwParentRow.getParent();
                    int pos =lv.getPositionForView(vwParentRow);

                    Button btnChild = (Button)vwParentRow.getChildAt(1);
                    Picture pictureToDisplay = getItem(pos);
                    addFav(pictureToDisplay);
                    btnChild.setText(pictureToDisplay.getUrl());

                }
            });
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

    public void addFav(Picture pic){
        RequestQueue queue = Volley.newRequestQueue(this.getContext().getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, Constant.API_URL_POST,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something with error response
                        error.printStackTrace();
                        //Snackbar.make(mCLayout,"Error",Snackbar.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", "http://itsalif.info");

                return params;
            }
        };

    }
}
