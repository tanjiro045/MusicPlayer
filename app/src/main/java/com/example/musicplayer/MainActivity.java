package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton, pauseButton, nextButton, prevButton;
    private int[] songResourceIds = {R.raw.song1, R.raw.song2, R.raw.song3};
    private int currentSongIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.song1); // Replace with your audio resource

        // Find your UI elements
        playButton = findViewById(R.id.play_button);
        pauseButton = findViewById(R.id.pause_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSongIndex < songResourceIds.length - 1) {
                    currentSongIndex++; // Move to the next song
                    playCurrentSong();
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSongIndex > 0) {
                    currentSongIndex--; // Move to the previous song
                    playCurrentSong();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    private void playCurrentSong() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(MainActivity.this, songResourceIds[currentSongIndex]);
        mediaPlayer.start();
    }
}
