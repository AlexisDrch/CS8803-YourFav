package com.yourfav;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    ListView pictureListView;
    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Picture> pictures = new ArrayList<>();

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        pictureListView = (ListView) findViewById(R.id.listView);

        // Get list of pictures
        getMyPictures2();



        PictureAdapter adapter = new PictureAdapter(MainActivity.this, pictures);
        pictureListView.setAdapter(adapter);

    }
    /**
     * Just temporary to ckeck the listview
     */
    private List<Picture> getMyPictures2(){
        getMyPictures();
        pictures.add(new Picture("http://www.telegraph.co.uk/travel/destination/article130148.ece/ALTERNATES/w620/parisguidetower.jpg",1));
        pictures.add(new Picture("http://www.traditours.com/images/Photos%20Angleterre/ForumLondonBridge.jpg",2));
        pictures.add(new Picture("http://tanned-allemagne.com/wp-content/uploads/2012/10/pano_rathaus_1280.jpg",3));
        pictures.add(new Picture("http://www.sejour-linguistique-lec.fr/wp-content/uploads/espagne-02.jpg",4));
        pictures.add(new Picture("http://retouralinnocence.com/wp-content/uploads/2013/05/Hotel-en-Italie-pour-les-Vacances2.jpg",5));
        pictures.add(new Picture("http://www.choisir-ma-destination.com/uploads/_large_russie-moscou2.jpg",6));

        for (Picture pic : pictures){
               // pic.setBitmap();
            }
            return pictures;

    }

    /**
     * Make a request on server to get all the pictures from server
     *
     */
    private void getMyPictures() {
        String url = Constant.API_URL_GET;
        StringRequest getPictureListRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray hits = jsonObject.getJSONArray("hits");
                            for (int i = 0; i< hits.length();i++){
                                JSONObject image = hits.getJSONObject(i);

                                final Picture picture = new Picture(image.getString("previewURL"),image.getInt("id"));
                                /*final Picture picture = new Picture((ImageView)findViewById(R.id.my_image_view));
                                picture.setUrl(image.getString("previewURL"));*/

                                Toast.makeText(getApplicationContext(), "requete get", Toast.LENGTH_LONG).show();
                                Log.e(TAG,"requete get");
                                pictures.add(picture);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                simpleErrorListener);

        requestQueue.add(getPictureListRequest);

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

    public void myClickHandler(View v)
    {
        //get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout)v.getParent();

        TextView child = (TextView)vwParentRow.getChildAt(0);
        Button btnChild = (Button)vwParentRow.getChildAt(1);
        btnChild.setText(child.getText());
        btnChild.setText("I've been clicked!");
    }
}

