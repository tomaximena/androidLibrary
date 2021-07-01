package com.example.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AllGamesActivity extends AppCompatActivity {

    private RecyclerView gamesRecView;
    private GameRecViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_games);

        // initialize the view from activity allgames layout
        gamesRecView = findViewById(R.id.gamesRecView);
        // initialize adapter
        adapter = new GameRecViewAdapter( this, "allGames" );
        
        gamesRecView.setAdapter(adapter);
        gamesRecView.setLayoutManager(new LinearLayoutManager(this));


        DBHelper dbHelper = new DBHelper(AllGamesActivity.this);
        ArrayList<Game> allGames = dbHelper.allGames();
        adapter.setGames(allGames);


    }
}