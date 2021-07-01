package com.example.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class WantToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_play);

        RecyclerView recyclerView = findViewById(R.id.wantToPlayRecView);
        GameRecViewAdapter adapter = new GameRecViewAdapter(this, "wantToPlay");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(WantToPlayActivity.this);
        ArrayList<Game> wantToPlayGames = dbHelper.wantToPlayGames();
        adapter.setGames(wantToPlayGames);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // TODO  see this
        startActivity(intent);
    }
    }
