package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.doanuddd.musicapp1.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.doanuddd.musicapp1.adapter.ViewPagerDisc;
import com.doanuddd.musicapp1.fragment.FragmentDisc;
import com.doanuddd.musicapp1.model.Song;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayingMusicActivity extends AppCompatActivity {

    public static ArrayList<Song> songArrayList = new ArrayList<>();
    private SQLiteDatabase db;
    private MediaPlayer mediaPlayer;
    private androidx.appcompat.widget.Toolbar toolbarplaynhac;
    private SeekBar seekBarnhac;
    private ImageView imageViewtim;
    private TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    private ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
            imageButtonlapnhac;
    ViewPager viewPagerplaynhac;
    private int dem = 0;
    private int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    private String taikhoan, matkhau, name, url;

    FragmentDisc fragment_disc;
    public static ViewPagerDisc adapterDisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_music);
        GetDataFromIntent();
        anhxa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        enventClick();
//        imageViewtim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (dem == 0){
//                    Animation animation = AnimationUtils.loadAnimation(PlayingMusicActivity.this, R.anim.anim_timclick);
//                    imageViewtim.setImageResource(R.drawable.iconloved);
//                    view.startAnimation(animation);
//                    if (songArrayList.size() > 0){
//                        insertYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat(), mangbaihat.get(position).getTenBaiHat(),
//                                mangbaihat.get(position).getTenCaSi(), mangbaihat.get(position).getHinhBaiHat(), mangbaihat.get(position).getLinkBaiHat());
//                    }else if (mangbaihetthuvienplaylist.size() > 0){
//                        insertYeuThich(taikhoan, mangbaihetthuvienplaylist.get(position).getIdBaiHat(), mangbaihetthuvienplaylist.get(position).getTenBaiHat(),
//                                mangbaihetthuvienplaylist.get(position).getTenCaSi(), mangbaihetthuvienplaylist.get(position).getHinhBaiHat(), mangbaihetthuvienplaylist.get(position).getLinkBaiHat());
//                    }else if (mangbaihatyeuthich.size() > 0){
//                        insertYeuThich(taikhoan, mangbaihatyeuthich.get(position).getIdBaiHat(), mangbaihatyeuthich.get(position).getTenBaiHat(),
//                                mangbaihatyeuthich.get(position).getTenCaSi(), mangbaihatyeuthich.get(position).getHinhBaiHat(), mangbaihatyeuthich.get(position).getLinkBaiHat());
//                    }
//                    dem++;
//
//                }else {
//                    imageViewtim.setImageResource(R.drawable.iconlove);
//                    if (mangbaihat.size() > 0){
//                        deleteYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
//                    }else if (mangbaihetthuvienplaylist.size() > 0){
//                        deleteYeuThich(taikhoan, mangbaihetthuvienplaylist.get(position).getIdBaiHat());
//                    }else if (mangbaihatyeuthich.size() > 0){
//                        deleteYeuThich(taikhoan, mangbaihatyeuthich.get(position).getIdBaiHat());
//                    }
//                    dem--;
//                }
//            }
//        });
    }
    private void enventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (songArrayList.size() > 0){
//                    fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHat());
                    fragment_disc.PlayNhac("aa");

                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);
        imageButtonplaypausenhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                }else {
                    mediaPlayer.start();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                }
            }
        });
        imageButtonlapnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkrandom == true){
                        checkrandom = false;
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                        repeat = true;
                    }else {
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        repeat = true;
                    }
                }else {
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtontronnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                        checkrandom = true;
                    }else {
                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        checkrandom = true;
                    }
                }else {
                    imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBarnhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonnexnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())){
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = songArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > songArrayList.size() - 1){
                            position = 0;
                        }
//                        checkYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
                        fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHat());
                        new playMP3().execute(songArrayList.get(position).getLinkBaiHat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageButtonpreviewnhac.setClickable(false);
                imageButtonnexnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpreviewnhac.setClickable(true);
                        imageButtonnexnhac.setClickable(true);
                    }
                }, 3000);
            }
        });
        imageButtonpreviewnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())) {
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position--;
                        if (position < 0) {
                            position = songArrayList.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
//                        checkYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
                        fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHat());
                        new playMP3().execute(songArrayList.get(position).getLinkBaiHat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageButtonpreviewnhac.setClickable(false);
                imageButtonnexnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpreviewnhac.setClickable(true);
                        imageButtonnexnhac.setClickable(true);
                    }
                }, 3000);
            }
        });
    }
    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songArrayList.clear();

        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                Song baiHat = intent.getParcelableExtra("cakhuc");
                songArrayList.add(baiHat);
            }else if (intent.hasExtra("listSong")){
                ArrayList<Song> songArrayFromIntent = intent.getParcelableArrayListExtra("listSong");
                songArrayList = songArrayFromIntent;
            }else if (intent.hasExtra("cakhucthuvien")){

            }else if (intent.hasExtra("cacbaihatthuvien")){

            }else if (intent.hasExtra(("localSong"))){

            }

            if (intent.hasExtra(("localSong"))){
                Song baiHat = intent.getParcelableExtra("localSong");
                songArrayList.add(baiHat);
                // an nut tai
            }

            if (intent.hasExtra(("listLocalSong"))){
                ArrayList<Song> songArrayFromIntent = intent.getParcelableArrayListExtra("listLocalSong");
                songArrayList = songArrayFromIntent;
                // an nut tai
            }
        }
    }

    private void anhxa() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekBartime);
        viewPagerplaynhac = findViewById(R.id.viewPagerdianhac);
        imageViewtim = findViewById(R.id.imageViewtimplaynhac);
        imageButtontronnhac = findViewById(R.id.imageButtontron);
        imageButtonpreviewnhac = findViewById(R.id.imageButtonpreview);
        imageButtonplaypausenhac = findViewById(R.id.imageButtonplaypause);
        imageButtonnexnhac = findViewById(R.id.imageButtonnext);
        imageButtonlapnhac = findViewById(R.id.imageButtonlap);
        textViewtatoltime = findViewById(R.id.textViewtimetotal);
        textViewcasi = findViewById(R.id.textViewtencasiplaynhac);
        textViewtennhac = findViewById(R.id.textViewtenbaihatplaynhac);
        textViewrunrime = findViewById(R.id.textViewruntime);

        fragment_disc = new FragmentDisc();
        adapterDisc = new ViewPagerDisc(getSupportFragmentManager());
        adapterDisc.AddFragment(fragment_disc);
        viewPagerplaynhac.setAdapter(adapterDisc);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

        fragment_disc = (FragmentDisc) adapterDisc.getItem(position);
        if (songArrayList.size() > 0){
//            checkYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
            getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
            new playMP3().onPostExecute(songArrayList.get(position).getLinkBaiHat());
            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        }

        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                songArrayList.clear();
                finish();
            }
        });

    }
    class playMP3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }
    private void TimeSong(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarnhac.setMax(mediaPlayer.getDuration());
        if (songArrayList.size() > 0){
            textViewtennhac.setText(songArrayList.get(position).getTenBaiHat());
            textViewcasi.setText(songArrayList.get(position).getCaSi());
        }
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBarnhac.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewrunrime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (songArrayList.size())) {
                        //imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                        position++;
                        if (repeat == true) {
                            position --;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > songArrayList.size() - 1) {
                            position = 0;
                        }
                        try {
//                            checkYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
                            fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHat());
                            new playMP3().execute(songArrayList.get(position).getLinkBaiHat());
                            getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    imageButtonpreviewnhac.setClickable(false);
                    imageButtonnexnhac.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonpreviewnhac.setClickable(true);
                            imageButtonnexnhac.setClickable(true);
                        }
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}