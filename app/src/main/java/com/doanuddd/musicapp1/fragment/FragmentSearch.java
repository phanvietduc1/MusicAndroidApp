package com.doanuddd.musicapp1.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.SearchAdapter;
import com.doanuddd.musicapp1.model.Song;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.ServiceApi;
import com.doanuddd.musicapp1.retrofit.SongApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    View view;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    TextView textView;
    SearchAdapter searchAdapter;
    ArrayList<Song> listSong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false);

        toolbar = view.findViewById(R.id.toilbartimkiem);
        recyclerView = view.findViewById(R.id.recyclerviewtimkiem);
        textView = view.findViewById(R.id.textviewtimkiemnull);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerView.setBackgroundColor(Color.BLACK);
                if (!s.trim().equals("")){
                    SearchSong(s);
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchSong(String query){
        ServiceApi serviceApi = ApiClient.self().retrofit.create(ServiceApi.class);
        Call<List<Song>> callback = serviceApi.getSongByKeyWord(query);
        callback.enqueue(new Callback<List<Song>>() {

            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                if (listSong.size() > 0){
                    searchAdapter = new SearchAdapter(getActivity(), listSong);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                    LinearLayout linearLayoutManager = new LinearLayout(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(searchAdapter);
                    textView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}