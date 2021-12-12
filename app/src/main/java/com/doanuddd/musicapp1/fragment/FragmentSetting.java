package com.doanuddd.musicapp1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.activity.HomeActivity;
import com.doanuddd.musicapp1.activity.LoginActivity;


public class FragmentSetting extends Fragment {

    View view;
    Button btn_logout;


    public FragmentSetting() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentSetting newInstance(String param1, String param2) {
        FragmentSetting fragment = new FragmentSetting();
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
        view =  inflater.inflate(R.layout.fragment_setting, container, false);
        anhxa();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void anhxa(){
        btn_logout = view.findViewById(R.id.btn_logout);
    }
}