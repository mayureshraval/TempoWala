package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ytvideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytvideo);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
//        youTubePlayerView.enterFullScreen();
        getLifecycle().addObserver(youTubePlayerView);



    }
}