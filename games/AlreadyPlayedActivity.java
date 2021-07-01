package com.example.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class AlreadyPlayedActivity extends AppCompatActivity {

    private RecyclerView alreadyPlayedRecView;
    private GameRecViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_played);

        alreadyPlayedRecView = findViewById(R.id.alreadyPlayedRecView);
        adapter = new GameRecViewAdapter(this, "alreadyPlayed");

        alreadyPlayedRecView.setAdapter(adapter);
        alreadyPlayedRecView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(AlreadyPlayedActivity.this);
        ArrayList<Game> alreadyPlayed = dbHelper.alreadyPlayedGames();
        adapter.setGames(alreadyPlayed);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}