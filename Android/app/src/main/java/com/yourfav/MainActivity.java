package com.yourfav;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<Picture> pictureList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private ProgressBar pgbLoading;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        pgbLoading = (ProgressBar) findViewById(R.id.a_main_pgb_loading);

        fragmentManager = getSupportFragmentManager();

        // Get info about my cellar
        getMyPictures();
    }

    private void displayPictureList() {
        // Create a fragment using factory method
        PictureListFragment pictureListFragment = PictureListFragment.newInstance(pictureList);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.a_main_lyt_fragment_container, pictureListFragment);
        transaction.commit();
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

                                final Picture picture = new Picture((ImageView)findViewById(R.id.my_image_view));
                                picture.setUrl(image.getString("previewURL"));

                                // Initialize a new RequestQueue instance
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                                // Initialize a new ImageRequest
                                ImageRequest imageRequest = new ImageRequest(
                                        picture.getUrl(), // Image URL
                                        new Response.Listener<Bitmap>() { // Bitmap listener
                                            @Override
                                            public void onResponse(Bitmap response) {
                                                // Do something with response
                                                picture.getImView().setImageBitmap(response);
                                            }
                                        },
                                        0, // Image width
                                        0, // Image height
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
                                requestQueue.add(imageRequest);

                                Toast.makeText(getApplicationContext(), "requete get", Toast.LENGTH_LONG).show();
                                Log.e(TAG,"requete get");
                                pictureList.add(picture);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Hide progress bar
                        pgbLoading.setVisibility(View.GONE);

                        // Display picture list fragment
                        displayPictureList();
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
