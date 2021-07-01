package com.example.games;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;
    public static final String APGAMES_TABLE = "APGAMES_TABLE";
    public static final String APGAME_ID = "APGAME_ID";
    public static final String APGAME_FID = "APGAME_FID";
    private static final String CPGAMES_TABLE = "CPGAMES_TABLE" ;
    private static final String FGAMES_TABLE = "FGAMES_TABLE";
    private static final String WPGAMES_TABLE = "WPGAMES_TABLE";
    private static final String CPGAME_ID = "CPGAME_ID";
    private static final String CPGAME_FID = "CPGAME_FID" ;
    private static final String FGAME_ID = "FGAME_ID";
    private static final String FGAME_FID = "FGAME_FID" ;
    private static final String WPGAME_ID = "WPGAME_ID" ;
    private static final String WPGAME_FID = "WPGAME_FID";
    public static final String GAMES_TABLE = "GAMES_TABLE";
    public static final String GAME_NAME = "GAME_NAME";
    public static final String GAME_DEVELOPER = "GAME_DEVELOPER";
    public static final String GAME_RATING = "GAME_RATING";
    public static final String GAME_DESCRIPTION = "GAME_DESCRIPTION";
    public static final String GAME_IMG = "GAME_IMG";
    public static final String GAME_ID = "GAME_ID";

    public ArrayList<Game> allGames = new ArrayList<Game>();
    public ArrayList<Game> alreadyPlayedGames = new ArrayList<Game>();
    public ArrayList<Game> currentlyPlayingGames = new ArrayList<Game>();
    public ArrayList<Game> favouriteGames = new ArrayList<Game>();
    public ArrayList<Game> wantToPlayGames = new ArrayList<Game>();


    public DBHelper(@Nullable Context context ) {
        super(context, "games.db", null, 1);
    }


    // is called the first time a db is accesed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatementGame = "CREATE TABLE " + GAMES_TABLE + "(" + GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GAME_NAME + " TEXT, " + GAME_DEVELOPER + " TEXT, " + GAME_RATING + " INT, " + GAME_DESCRIPTION + " TEXT, " + GAME_IMG + " VARCHAR(3000) )";
        String createTableStatementAPGame = "CREATE TABLE " + APGAMES_TABLE + " (" + APGAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + APGAME_FID + " INTEGER, " + " FOREIGN KEY (APGAME_FID) REFERENCES GAMES_TABLE (GAME_ID) ON DELETE CASCADE)";
        String createTableStatementCPGame = "CREATE TABLE " + CPGAMES_TABLE+ " (" + CPGAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CPGAME_FID + " INTEGER, " + " FOREIGN KEY (CPGAME_FID) REFERENCES GAMES_TABLE (GAME_ID) ON DELETE CASCADE)";
        String createTableStatementFGame = "CREATE TABLE " + FGAMES_TABLE + " (" + FGAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FGAME_FID + " INTEGER, " + " FOREIGN KEY (FGAME_FID) REFERENCES GAMES_TABLE (GAME_ID) ON DELETE CASCADE)";
        String createTableStatementWPGame = "CREATE TABLE " + WPGAMES_TABLE + " (" + WPGAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WPGAME_FID + " INTEGER, " + " FOREIGN KEY (WPGAME_FID) REFERENCES GAMES_TABLE (GAME_ID) ON DELETE CASCADE)";
        String initialData = "INSERT INTO " + GAMES_TABLE + " VALUES  ( 1, 'test', 'test', 1 , 'test' , 'test')";
        String initialData2 = "INSERT INTO " + APGAMES_TABLE + " VALUES  ( 1, 1)";
        String initialData3 = "INSERT INTO " + CPGAMES_TABLE + " VALUES  ( 1, 1)";
        String initialData4 = "INSERT INTO " + FGAMES_TABLE + " VALUES  ( 1, 1)";
        String initialData5 = "INSERT INTO " + WPGAMES_TABLE + " VALUES  ( 1, 1)";
        db.execSQL(createTableStatementGame);
        db.execSQL(createTableStatementAPGame);
        db.execSQL(createTableStatementCPGame);
        db.execSQL(createTableStatementFGame);
        db.execSQL(createTableStatementWPGame);
        db.execSQL(initialData);
        db.execSQL(initialData2);
        db.execSQL(initialData3);
        db.execSQL(initialData4);
        db.execSQL(initialData5);
    }

    // when the db version number changes, if I add new tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }



    // for adding data
    public boolean addGDB (Game game){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(GAME_NAME, game.getName());
        cv.put(GAME_DEVELOPER, game.getDeveloper());
        cv.put(GAME_RATING, game.getRating());
        cv.put(GAME_DESCRIPTION, game.getDescription());
        cv.put(GAME_IMG, game.getImageUrl());

        long insert = db.insert(GAMES_TABLE, null ,cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }

        }

    // for deleting data from Games table, might need and use it as a future feature
    public boolean delete(Game game){
        SQLiteDatabase db = this.getWritableDatabase();
        String qString = "DELETE FROM " + GAMES_TABLE + " WHERE " + GAME_ID + " = " + game.getId();

        Cursor cursor = db.rawQuery(qString,null);
        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }

    }

    // for showing everything
        public ArrayList<Game> allGames() {
            //query for db
            String qString = "SELECT * FROM " + GAMES_TABLE;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(qString, null);
            // see if succesful
            if (cursor.moveToFirst()) {
                // loop through the cursor
                do{
                int gameID = cursor.getInt(0);
                String gameName = cursor.getString(1);
                String gameDev = cursor.getString(2);
                int gameRating = cursor.getInt(3);
                String gameDesc = cursor.getString(4);
                String gameImg = cursor.getString(5);

                Game newGame = new Game(gameID, gameName, gameDev, gameRating, gameDesc, gameImg);
                allGames.add(newGame);
            }
            while (cursor.moveToNext()) ;
        }

        else{

            }
            cursor.close();
            db.close();
        return allGames;
        }

        // for the alreadyplayed
        public ArrayList<Game> alreadyPlayedGames(){
            String qString = "SELECT * FROM " + GAMES_TABLE + " , " + APGAMES_TABLE +
            " WHERE "  + GAME_ID + " = " + APGAME_FID;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(qString, null);
            // see if succesful
            if(cursor.moveToFirst()){
                // loop through the cursor
                do {
                    int gameID = cursor.getInt(0);
                    String gameName = cursor.getString(1);
                    String gameDev= cursor.getString(2);
                    int gameRating= cursor.getInt(3);
                    String gameDesc= cursor.getString(4);
                    String gameImg = cursor.getString(5);

                    Game newGame = new Game(gameID, gameName, gameDev,  gameRating, gameDesc, gameImg);
                    alreadyPlayedGames.add(newGame);

                } while (cursor.moveToNext());
            }
            else{

            }
            cursor.close();
            db.close();
            return alreadyPlayedGames;
        }

        //for the currently playing
        public ArrayList<Game> currentlyPlayingGames(){
            String qString = "SELECT * FROM " + GAMES_TABLE + " , " + CPGAMES_TABLE +
                    " WHERE "  + GAME_ID + " = " + CPGAME_FID;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(qString, null);
            // see if succesful
            if(cursor.moveToFirst()){
                // loop through the cursor
                do {
                    int gameID = cursor.getInt(0);
                    String gameName = cursor.getString(1);
                    String gameDev= cursor.getString(2);
                    int gameRating= cursor.getInt(3);
                    String gameDesc= cursor.getString(4);
                    String gameImg = cursor.getString(5);

                    Game newGame = new Game(gameID, gameName, gameDev,  gameRating, gameDesc, gameImg);
                    currentlyPlayingGames.add(newGame);

                } while (cursor.moveToNext());
            }
            else{

            }
            cursor.close();
            db.close();
            return currentlyPlayingGames;
        }

    //for the favourite playing
    public ArrayList<Game> favouriteGames(){
        String qString = "SELECT * FROM " + GAMES_TABLE + " , " + FGAMES_TABLE +
                " WHERE "  + GAME_ID + " = " + FGAME_FID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qString, null);
        // see if succesful
        if(cursor.moveToFirst()){
            // loop through the cursor
            do {
                int gameID = cursor.getInt(0);
                String gameName = cursor.getString(1);
                String gameDev= cursor.getString(2);
                int gameRating= cursor.getInt(3);
                String gameDesc= cursor.getString(4);
                String gameImg = cursor.getString(5);

                Game newGame = new Game(gameID, gameName, gameDev,  gameRating, gameDesc, gameImg);
                favouriteGames.add(newGame);

            } while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return favouriteGames;
    }

    //for the want to play
    public ArrayList<Game> wantToPlayGames(){
        String qString = "SELECT * FROM " + GAMES_TABLE + " , " + WPGAMES_TABLE +
                " WHERE "  + GAME_ID + " = " + WPGAME_FID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qString, null);
        // see if succesful
        if(cursor.moveToFirst()){
            // loop through the cursor
            do {
                int gameID = cursor.getInt(0);
                String gameName = cursor.getString(1);
                String gameDev= cursor.getString(2);
                int gameRating= cursor.getInt(3);
                String gameDesc= cursor.getString(4);
                String gameImg = cursor.getString(5);

                Game newGame = new Game(gameID, gameName, gameDev,  gameRating, gameDesc, gameImg);
                wantToPlayGames.add(newGame);

            } while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return wantToPlayGames;
    }

    public Game getGameByID (int id, ArrayList<Game> holder) {
        for (Game b: holder){
            if(b.getId()==id){
                return b;
            }
        }
        return null;
    }

        public boolean addToAlreadyPlayed(Game game){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(APGAME_FID, game.getId());

        long insert = db.insert(APGAMES_TABLE, null ,cv);
            if(insert == -1){
                return false;
            }
            else{
                return true;
            }
    }


        public boolean removeFromAlreadyPlayed (Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        String qString = "DELETE FROM " + APGAMES_TABLE + " WHERE " + APGAME_FID + " = " + game.getId();
        try {
            db.execSQL(qString);
            return true;
        }
        catch(SQLException e){
            return false;
            }
       }

        public boolean addTocurrentlyPlayingGames(Game game){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CPGAME_FID, game.getId());

            long insert = db.insert(CPGAMES_TABLE, null ,cv);
            if(insert == -1){
                return false;
            }
            else{
                return true;
            } }
        public boolean removeFromcurrentlyPlayingGames (Game game) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qString = "DELETE FROM " + CPGAMES_TABLE + " WHERE " + CPGAME_FID + " = " + game.getId();

            try {
                db.execSQL(qString);
                return true;
            }
            catch(SQLException e){
                return false;
            }}

        public boolean addTofavouriteGames(Game game){ SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(FGAME_FID, game.getId());

            long insert = db.insert(FGAMES_TABLE, null ,cv);
            if(insert == -1){
                return false;
            }
            else{
                return true;
            } }
        public boolean removeFromfavouriteGames (Game game) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qString = "DELETE FROM " + FGAMES_TABLE + " WHERE " + FGAME_FID + " = " + game.getId();

            try {
                db.execSQL(qString);
                return true;
            }
            catch(SQLException e){
                return false;
            }}

        public boolean addTowantToPlayGames(Game game){ SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(WPGAME_FID, game.getId());

            long insert = db.insert(WPGAMES_TABLE, null ,cv);
            if(insert == -1){
                return false;
            }
            else{
                return true;
            } }
        public boolean removeFromwantToPlayGames (Game game) {

        SQLiteDatabase db = this.getWritableDatabase();
        String qString = "DELETE FROM " + WPGAMES_TABLE + " WHERE " + WPGAME_FID + " = " + game.getId();

            try {
                db.execSQL(qString);
                return true;
            }
            catch(SQLException e){
                return false;
            }}

}

