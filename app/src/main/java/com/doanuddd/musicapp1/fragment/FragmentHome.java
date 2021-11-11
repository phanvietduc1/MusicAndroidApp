package com.doanuddd.musicapp1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.activity.HomeActivity;
import com.doanuddd.musicapp1.adapter.ListSongAdapter;
import com.doanuddd.musicapp1.adapter.ListSongAdapter2;
import com.doanuddd.musicapp1.adapter.recrycleSongList;
import com.doanuddd.musicapp1.model.Song;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.SongApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

    View view;
    RecyclerView listSongView;
    ListSongAdapter listSongAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home,container,false);
        anhxa();
        getData();
        return view;
    }

    private void anhxa() {
        listSongView = view.findViewById(R.id.viewListSong);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void getData(){

        SongApi songApi = ApiClient.self().retrofit.create(SongApi.class);

        retrofit2.Call<List<Song>> call = songApi.getSong();

        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
//                listSongAdapter = new ListSongAdapter2(getActivity(), songArrayList);
                listSongAdapter = new ListSongAdapter(songArrayList, getActivity());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                listSongView.setLayoutManager(layoutManager);
                listSongView.setAdapter(listSongAdapter);

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });

    }
}