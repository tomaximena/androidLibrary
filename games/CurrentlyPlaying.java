package com.example.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class CurrentlyPlaying extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_playing);

        RecyclerView recyclerView = findViewById(R.id.currentlyPlaying);
        GameRecViewAdapter adapter = new GameRecViewAdapter(this, "currentlyPlaying");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(CurrentlyPlaying.this);
        ArrayList<Game> currentlyPlaying = dbHelper.currentlyPlayingGames();
        adapter.setGames(currentlyPlaying);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // TODO  see this
        startActivity(intent);
    }
}
