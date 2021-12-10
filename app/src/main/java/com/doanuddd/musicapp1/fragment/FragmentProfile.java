package com.doanuddd.musicapp1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.activity.HomeActivity;
import com.doanuddd.musicapp1.activity.LoginActivity;
import com.squareup.picasso.Picasso;


public class FragmentProfile extends Fragment {

    HomeActivity hm;
    View view;
    EditText edname, edemail;


    public FragmentProfile() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        Init();

        return view;
    }

    private void Init() {
        edname = view.findViewById(R.id.edit_name_profile);
        edemail = view.findViewById(R.id.edit_email_profile);
        hm = (HomeActivity) getActivity();
        edname.setText(hm.getName());
        edemail.setText(hm.getEmail());
    }

}