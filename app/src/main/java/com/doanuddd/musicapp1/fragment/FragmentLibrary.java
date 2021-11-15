package com.doanuddd.musicapp1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.doanuddd.musicapp1.R;

public class FragmentLibrary extends Fragment {

    View view;


    public FragmentLibrary() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentLibrary newInstance(String param1, String param2) {
        FragmentLibrary fragment = new FragmentLibrary();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_library, container, false);
        return view;
    }
}