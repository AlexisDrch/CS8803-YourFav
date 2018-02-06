package com.yourfav;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
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
        pictures.add(new Picture("http://www.telegraph.co.uk/travel/destination/article130148.ece/ALTERNATES/w620/parisguidetower.jpg"));
        pictures.add(new Picture("http://www.traditours.com/images/Photos%20Angleterre/ForumLondonBridge.jpg"));
        pictures.add(new Picture("http://tanned-allemagne.com/wp-content/uploads/2012/10/pano_rathaus_1280.jpg"));
        pictures.add(new Picture("http://www.sejour-linguistique-lec.fr/wp-content/uploads/espagne-02.jpg"));
        pictures.add(new Picture("http://retouralinnocence.com/wp-content/uploads/2013/05/Hotel-en-Italie-pour-les-Vacances2.jpg"));
        pictures.add(new Picture("http://www.choisir-ma-destination.com/uploads/_large_russie-moscou2.jpg"));

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
        String url = Constant.API_URL;
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

                                final Picture picture = new Picture(image.getString("previewURL"));
                                /*final Picture picture = new Picture((ImageView)findViewById(R.id.my_image_view));
                                picture.setUrl(image.getString("previewURL"));
*/
                                // Initialize a new RequestQueue instance
                                RequestQueue requestQueuePicture = Volley.newRequestQueue(getApplicationContext());

                                // Initialize a new ImageRequest
                                ImageRequest imageRequest = new ImageRequest(
                                        picture.getUrl(), // Image URL
                                        new Response.Listener<Bitmap>() { // Bitmap listener
                                            @Override
                                            public void onResponse(Bitmap response) {
                                                // Do something with response
                                                picture.setBitmap(response);
                                            }
                                        },
                                        50, // Image width
                                        50, // Image height
                                        ImageView.ScaleType.CENTER_CROP, // Image scale type
                                        Bitmap.Config.RGB_565, //Image decode configuration
                                        new Response.ErrorListener() { // Error listener
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // Do something with error response
                                                error.printStackTrace();
                                                //Snackbar.make(mCLayout,"Error",Snackbar.LENGTH_LONG).show();
                                            }
                                        }
                                );

                                // Add ImageRequest to the RequestQueue
                                requestQueuePicture.add(imageRequest);

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
}
