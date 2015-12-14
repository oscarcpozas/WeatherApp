package com.study.jam.weather.ui.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.study.jam.weather.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends DialogFragment {

    /**
     * The Marker Argument
     */
    private static final String ARG_PARAM1 = "data";

    /**
     * The data to show in the DialogFragment
     */
    private String mData;

    /**
     * The details textview
     */
    private TextView mDetailsTextView;

    public static DetailsFragment newInstance(String data) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, data);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mData = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        mDetailsTextView = (TextView) view.findViewById(R.id.fragment_details_title);
        mDetailsTextView.setText(mData);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

}
