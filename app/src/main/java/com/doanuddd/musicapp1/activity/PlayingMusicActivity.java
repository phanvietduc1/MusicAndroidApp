package com.doanuddd.musicapp1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import com.doanuddd.musicapp1.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.doanuddd.musicapp1.model.Song;

import java.util.ArrayList;

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
//    Fragment_dia_nhac fragment_dia_nhac;
//    public static ViewPagerDiaNhac adapternhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_music);
        GetDataFromIntent();
        anhxa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
    }
    private void GetDataFromIntent() {
        Intent intent = getIntent();
        songArrayList.clear();

        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                Song baiHat = intent.getParcelableExtra("cakhuc");
                songArrayList.add(baiHat);
            }else if (intent.hasExtra("cacbaihat")){

            }else if (intent.hasExtra("cakhucthuvien")){

            }else if (intent.hasExtra("cacbaihatthuvien")){

            }else if (intent.hasExtra(("cakhucyeuthich"))){

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

//        fragment_dia_nhac = new Fragment_dia_nhac();
//        adapternhac = new ViewPagerDiaNhac(getSupportFragmentManager());
//        adapternhac.AddFragment(fragment_dia_nhac);
//        viewPagerplaynhac.setAdapter(adapternhac);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

//        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(position);
//        if (mangbaihat.size() > 0){
//            checkYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
//            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
//            new playMP3().onPostExecute(mangbaihat.get(position).getLinkBaiHat());
//            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
//        }else if (mangbaihetthuvienplaylist.size() > 0){
//            checkYeuThich(taikhoan, mangbaihetthuvienplaylist.get(position).getIdBaiHat());
//            getSupportActionBar().setTitle(mangbaihetthuvienplaylist.get(position).getTenBaiHat());
//            new playMP3().onPostExecute(mangbaihetthuvienplaylist.get(position).getLinkBaiHat());
//            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
//        }else if (mangbaihatyeuthich.size() > 0){
//            checkYeuThich(taikhoan, mangbaihatyeuthich.get(position).getIdBaiHat());
//            getSupportActionBar().setTitle(mangbaihatyeuthich.get(position).getTenBaiHat());
//            new playMP3().onPostExecute(mangbaihatyeuthich.get(position).getLinkBaiHat());
//            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
//        }

        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                songArrayList.clear();
                finish();
            }
        });

    }
}