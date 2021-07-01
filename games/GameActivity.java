package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;


import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    public static final String GAME_ID_KEY = "gameID";
    private TextView textViewGameNameAG, textViewDeveloperAG, textViewRatingAG, textViewDescriptionAG;
    private ImageView imgGameAG;
    private Button btnCurrentlyPlaying, btnAddToWishlist, btnAlreadyPlayed, btnAddToFavourites;
    private DBHelper dbHelper = new DBHelper(GameActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViews();

        // get the intent that got this launched
        Intent intent = getIntent();
        if (null != intent){
        int gameID = intent.getIntExtra(GAME_ID_KEY, -1);
        if(gameID != -1){
        ArrayList<Game> holder = dbHelper.allGames();
        Game incomingGame = dbHelper.getGameByID(gameID, holder);
        if(null != incomingGame){
            setData(incomingGame);
            handleAlreadyPlayed(incomingGame);
            handleWantToPlayGames(incomingGame);
            handleCurrentlyPlaying(incomingGame);
            handleFavouriteGames(incomingGame);
        }
        }
        }
   }

    private void setData(Game incomingGame) {
        textViewGameNameAG.setText(incomingGame.getName());
        textViewDeveloperAG.setText(incomingGame.getDeveloper());
        textViewRatingAG.setText(String.valueOf(incomingGame.getRating()));
        textViewDescriptionAG.setText(incomingGame.getDescription());
        Glide.with(this)
                .asBitmap().load(incomingGame.getImageUrl())
                .into(imgGameAG);
    }



    //Enable and disable button and add game to want to play

    private void handleWantToPlayGames(Game game){
        ArrayList<Game> handleWPGames = new ArrayList<Game>();
        handleWPGames = dbHelper.wantToPlayGames();
        boolean existsInWanttoPlay = false;

        for(Game b: handleWPGames){
            if (b.getId()==game.getId()) {
                existsInWanttoPlay = true;
            }

        }

        if (existsInWanttoPlay){
            btnAddToWishlist.setEnabled(false);
        }
        else {
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.addTowantToPlayGames(game)) {
                        Toast.makeText(GameActivity.this, "Game added", Toast.LENGTH_SHORT).show();
                    }
                    else { Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();}
                }
            });
        }


    }

   // Enable and disable button and add game to currently play

    private void handleCurrentlyPlaying(Game game){
        ArrayList<Game> handleCPGames = new ArrayList<Game>();
        handleCPGames = dbHelper.currentlyPlayingGames();
        boolean existsInCurrentlyPlaying = false;

        for(Game b: handleCPGames){
            if (b.getId()==game.getId()) {
                existsInCurrentlyPlaying = true;
            }

        }

        if (existsInCurrentlyPlaying){
            btnCurrentlyPlaying.setEnabled(false);
        }
        else {
            btnCurrentlyPlaying.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.addTocurrentlyPlayingGames(game)) {
                        Toast.makeText(GameActivity.this, "Game added", Toast.LENGTH_SHORT).show();

                    }
                    else { Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();}
                }
            });
        }
    }

   // Enable and disable button and add game to favorites

    private void handleFavouriteGames(Game game){
        ArrayList<Game> handleFavouriteGames = new ArrayList<Game>();
        handleFavouriteGames = dbHelper.favouriteGames();
        boolean existsInFavourites = false;

        for(Game b: handleFavouriteGames){
            if (b.getId()==game.getId()) {
                existsInFavourites = true;
            }

        }

        if (existsInFavourites){
            btnAddToFavourites.setEnabled(false);
        }
        else {
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.addTofavouriteGames(game)) {
                        Toast.makeText(GameActivity.this, "Game added", Toast.LENGTH_SHORT).show();

                    }
                    else { Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();}
                }
            });
        }
    }

    // Enable and disable button and add game to already played

    private void handleAlreadyPlayed(Game game){

        ArrayList<Game> handleAlreadyPlayedGames = new ArrayList<Game>();
        handleAlreadyPlayedGames =  dbHelper.alreadyPlayedGames();

        boolean existsInAlreadyPlayed = false;

        for(Game b: handleAlreadyPlayedGames){
            if (b.getId()==game.getId()) {
                existsInAlreadyPlayed = true;
            }

        }

        if (existsInAlreadyPlayed){
            btnAlreadyPlayed.setEnabled(false);
        }
        else {
            btnAlreadyPlayed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dbHelper.addToAlreadyPlayed(game)) {
                        Toast.makeText(GameActivity.this, "Game added", Toast.LENGTH_SHORT).show();

                    }
                    else { Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();}
                }
            });
        }
    }


    private void initViews(){
        textViewGameNameAG = findViewById(R.id.textViewGameNameAG);
        textViewDeveloperAG = findViewById(R.id.textViewDeveloperAG);
        textViewRatingAG = findViewById(R.id.textViewRatingAG);
        textViewDescriptionAG = findViewById(R.id.textViewDescriptionAG);

        imgGameAG = findViewById(R.id.imgGameAG);

        btnCurrentlyPlaying = findViewById(R.id.btnCurrentlyPlaying);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
        btnAlreadyPlayed = findViewById(R.id.btnAlreadyPlayed);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);

    }



}


