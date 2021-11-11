package com.doanuddd.musicapp1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.activity.HomeActivity;
import com.doanuddd.musicapp1.adapter.recrycleSongList;
import com.doanuddd.musicapp1.model.Song;
import com.doanuddd.musicapp1.retrofit.ApiClient;
import com.doanuddd.musicapp1.retrofit.ApiInterface;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private recrycleSongList myAdapter;
    private RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);

//        Call<List<Song>> call = service.getProducts();
//
//        call.enqueue(new Callback<List<Song>>() {
//            @Override
//            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
//                loadDataList(response.body());
//
//                Toast.makeText(getActivity(), "mes123: ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Song>> call, Throwable t) {
//                Toast.makeText(getActivity(), "mes123: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadDataList(List<Song> songList) {
//        recyclerView = recyclerView.findViewById(R.id.listHomeSongView);
//        myAdapter = new recrycleSongList(songList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
}