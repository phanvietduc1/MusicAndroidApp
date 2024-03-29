package com.doanuddd.musicapp1.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.ListArtistAdapter;
import com.doanuddd.musicapp1.adapter.ListGenreAdapter;
import com.doanuddd.musicapp1.adapter.ListSongAdapter;
import com.doanuddd.musicapp1.model.Artist;
import com.doanuddd.musicapp1.model.Genre;
import com.doanuddd.musicapp1.model.Song;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.ArtistApi;
import com.doanuddd.musicapp1.retrofit.GenreApi;
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
    RecyclerView listArtistView;;
    RecyclerView listGenreView;
    ListSongAdapter listSongAdapter;
    ListArtistAdapter listArtistAdapter;
    ListGenreAdapter listGenreAdapter;

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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa();
        getData();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void anhxa() {
        listSongView = view.findViewById(R.id.viewListSong);
        listArtistView = view.findViewById(R.id.viewListArtist);
        listGenreView = view.findViewById(R.id.viewListGenre);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void getData() {

        SongApi songApi = ApiClient.self().retrofit.create(SongApi.class);
        ArtistApi artistApi = ApiClient.self().retrofit.create(ArtistApi.class);
        GenreApi genreApi = ApiClient.self().retrofit.create(GenreApi.class);

        retrofit2.Call<List<Song>> call = songApi.getSong();
        retrofit2.Call<List<Artist>> callArtist = artistApi.getArtist();
        retrofit2.Call<List<Genre>> callGenre = genreApi.getGenre();

        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(songArrayList, getActivity());
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                listSongView.setLayoutManager(linearLayoutManager);
                listSongView.setAdapter(listSongAdapter);

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });

        callArtist.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                ArrayList<Artist> artistArrayList = (ArrayList<Artist>) response.body();
                listArtistAdapter = new ListArtistAdapter(artistArrayList, getActivity());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                listArtistView.setLayoutManager(layoutManager);
                listArtistView.setAdapter(listArtistAdapter);
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {

            }
        });

        callGenre.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                ArrayList<Genre> genreArrayList = (ArrayList<Genre>) response.body();
                listGenreAdapter = new ListGenreAdapter(genreArrayList, getActivity());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                listGenreView.setLayoutManager(layoutManager);
                listGenreView.setAdapter(listGenreAdapter);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }
}