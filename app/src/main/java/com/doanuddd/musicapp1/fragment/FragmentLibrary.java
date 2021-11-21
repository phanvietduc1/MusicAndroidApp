package com.doanuddd.musicapp1.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.ListLocalSongAdapter;
import com.doanuddd.musicapp1.adapter.SearchAdapter;
import com.doanuddd.musicapp1.model.Song;

import java.util.ArrayList;

public class FragmentLibrary extends Fragment {

    View view;
    private static final int MY_PERMISSION_REQUEST=1;
    ListLocalSongAdapter adapter;
    ArrayList<Song> arrayList;
    RecyclerView recyclerView;
    byte[] art;

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
        recyclerView = view.findViewById(R.id.recyclerviewtv);
        if (getArguments() != null) {
        }
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
        }else
        {
            doStuff();
        }
        return view;
    }
    public void getMusic(){
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION
        };
        Cursor songCursor = contentResolver.query(songUri,projection, MediaStore.Audio.Media.DATA + " like ? ", new String[]{"%Download%"},null);
        if (songCursor!=null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int path = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();
            do{
                String pathId = songCursor.getString(path);
                metaRetriver.setDataSource(pathId);
                Song song = new Song();
                song.setTenBaiHat(songCursor.getString(songTitle));
                song.setCaSi(songCursor.getString(songArtist));
                art = metaRetriver.getEmbeddedPicture();
                Bitmap songImage = BitmapFactory.decodeByteArray(art, 0, art.length);
                song.setHinhBaiHatBit(songImage);
                arrayList.add(song);
            }while(songCursor.moveToNext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_REQUEST:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getActivity(),"Permission granted!",Toast.LENGTH_SHORT).show();
                        doStuff();
                    }else
                    {
                        Toast.makeText(getActivity(),"No permission granted!",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }
    }
    public void doStuff(){
        //listView = (ListView) view.findViewById(R.id.listView);
        arrayList= new ArrayList<>();
        getMusic();
        adapter = new ListLocalSongAdapter(getActivity(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //listView.setAdapter(adapter);
    }
}