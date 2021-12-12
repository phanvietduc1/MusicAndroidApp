package com.doanuddd.musicapp1.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.doanuddd.musicapp1.R;
import com.doanuddd.musicapp1.adapter.ViewPagerDisc;
import com.doanuddd.musicapp1.fragment.FragmentDisc;
import com.doanuddd.musicapp1.model.Song;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;

    public static ArrayList<Song> songArrayList = new ArrayList<>();
    private SQLiteDatabase db;
    private MediaPlayer mediaPlayer;
    private androidx.appcompat.widget.Toolbar toolbarplaynhac;
    private SeekBar seekBarnhac;
    private TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    private ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
            imageButtonlapnhac, imageButtonDownload;
    ViewPager viewPagerplaynhac;
    private int dem = 0;
    private int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    private String taikhoan, matkhau, name, url;

    FragmentDisc fragment_disc;
    public static ViewPagerDisc adapterDisc;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        intent = getIntent();
        GetDataFromIntent();
        anhxa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        enventClick();
    }

    private void enventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (songArrayList.size() > 0) {
                    setImageDisc();
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);
        imageButtonplaypausenhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                } else {
                    mediaPlayer.start();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                }
            }
        });
        imageButtonlapnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                        repeat = true;
                    } else {
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        repeat = true;
                    }
                } else {
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtontronnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                        checkrandom = true;
                    } else {
                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        checkrandom = true;
                    }
                } else {
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
                if (songArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())) {
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = songArrayList.size();
                            }
                            position -= 1;
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
//                        checkYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
                        setImageDisc();
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
                        setImageDisc();

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

        imageButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    } else {
                        startDownloading();
                    }
                } else {
                    startDownloading();
                }
            }
        });
    }

    private void startDownloading() {
        String url = songArrayList.get(position).getLinkBaiHat();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);

        request.setTitle("Download");
        request.setDescription("Downloading song...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ok.mp3");

        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownloading();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void GetDataFromIntent() {

        songArrayList.clear();

        if (intent != null) {
            if (intent.hasExtra("song")) {
                Song baiHat = intent.getParcelableExtra("song");
                songArrayList.add(baiHat);
//                imageButtonDownload.setVisibility(View.VISIBLE);
            }
            
            if (intent.hasExtra("listSong")){
                ArrayList<Song> songArrayFromIntent = intent.getParcelableArrayListExtra("listSong");
                songArrayList = songArrayFromIntent;
//                imageButtonDownload.setVisibility(View.VISIBLE);
            }

            if (intent.hasExtra(("localSong"))){
                Song baiHat = intent.getParcelableExtra("localSong");
                songArrayList.add(baiHat);
                // an nut tai
//                imageButtonDownload.setVisibility(View.GONE);
            }

            if (intent.hasExtra(("listLocalSong"))){
                ArrayList<Song> songArrayFromIntent = intent.getParcelableArrayListExtra("listLocalSong");
                songArrayList = songArrayFromIntent;
                // an nut tai
//                imageButtonDownload.setVisibility(View.GONE);
            }
        }
    }

    private void setImageDisc(){
        if (intent != null) {
            if (intent.hasExtra("song")) {
                fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHat());
            }
            if (intent.hasExtra("listSong")){
                fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHat());
            }
            if (intent.hasExtra(("localSong"))){
                fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHatBit());
            }
            if (intent.hasExtra(("listLocalSong"))){
                fragment_disc.PlayNhac(songArrayList.get(position).getHinhBaiHatBit());
            }
        }
    }

    private void anhxa() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekBartime);
        viewPagerplaynhac = findViewById(R.id.viewPagerdianhac);
        imageButtonDownload = findViewById(R.id.btn_download);
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
        if (songArrayList.size() > 0) {
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

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarnhac.setMax(mediaPlayer.getDuration());
        if (songArrayList.size() > 0) {
            textViewtennhac.setText(songArrayList.get(position).getTenBaiHat());
            textViewcasi.setText(songArrayList.get(position).getCaSi());
        }
    }

    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
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
                if (mediaPlayer.isPlaying()) {
                    //Hien anh bai hat len dia
                    setImageDisc();
                }
                if (next == true) {
                    if (position < (songArrayList.size())) {
                        //imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
                        position++;
                        if (repeat == true) {
                            position--;
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
                            setImageDisc();
                            new playMP3().execute(songArrayList.get(position).getLinkBaiHat());
                            getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        } catch (Exception e) {
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
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}