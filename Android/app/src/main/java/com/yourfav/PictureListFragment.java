package com.yourfav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by Vanessa on 28.01.2018.
 */

public class PictureListFragment extends Fragment {

    /**
     * Fragment that displays the list of pictures send as argument with {@link #BUNDLE_PARAM_PICTURES} key
     */
    public static final String BUNDLE_PARAM_PICTURES = "BUNDLE_PARAM_PICTURES";
    private String mParam1;

    private ListView ltvPictures;
    private ArrayList<Picture> pictures;
    private PictureAdapter pictureAdapter;

    public PictureListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided
     * parameters.
     *
     * @param pictures List of pictures to display
     * @return A new instance of fragment PictureListFragment.
     */
    public static PictureListFragment newInstance(ArrayList<Picture> pictures) {
        PictureListFragment fragment = new PictureListFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_PARAM_PICTURES, pictures);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Get list of pictures to display from fragment arguments
            pictures = (ArrayList<Picture>) getArguments().getSerializable(BUNDLE_PARAM_PICTURES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_picture_list, container, false);

        ltvPictures = (ListView) rootView.findViewById(R.id.f_picture_lis_ltv_pictures);
        pictureAdapter = new PictureAdapter(getContext(), pictures);
        ltvPictures.setAdapter(pictureAdapter);

        return rootView;
    }

}
