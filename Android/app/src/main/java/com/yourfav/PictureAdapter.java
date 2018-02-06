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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

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
                    btnChild.setText("Added to your favorites");

                }
            });
            convertView.setTag(viewHolder);
        }

        Picture pictureToDisplay = getItem(position);

        //fill our view
        viewHolder.button.setText("Like");
        //viewHolder.picture.setImageBitmap(pictureToDisplay.getBitmap());
        //viewHolder.picture.setImageResource(R.drawable.yellow_flower);
        Picasso.with(viewHolder.picture.getContext()).load(pictureToDisplay.getUrl()).centerCrop().fit().into(viewHolder.picture);


        return convertView;
    }

    public void addFav(Picture pic){
        RequestQueue queue = Volley.newRequestQueue(this.getContext().getApplicationContext());
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", String.valueOf(pic.getId()));
            obj.put("url", pic.getUrl());
            obj.put("user", "1");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String obj_str = obj.toString();
        //String obj_str="{\"id\":\""+String.valueOf(pic.getId())+"\",\"url\":\""+pic.getUrl()+"\",\"user\":1}";
       // JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,Constant.API_URL_POST,obj,
        StringRequest req = new StringRequest(Request.Method.POST,Constant.API_URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, simpleErrorListener) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return obj_str == null ? null : obj_str.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", obj_str, "utf-8");
                    return null;
                }
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(req);

    }

    /**
     * Simple error listener that just log error in logcat
     */
    private Response.ErrorListener simpleErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, " "+error.getLocalizedMessage());
        }
    };
}
