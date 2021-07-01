package com.example.games;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class AddingGamesActivity extends AppCompatActivity {

    private Button btnAddNewGame;
    private EditText txtAddGameName, txtAddDeveloper, txtAddRating, txtAddDescription, txtAddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initViews();


        btnAddNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game;

                try {
                    game = new Game(-1, txtAddGameName.getText().toString(), txtAddDeveloper.getText().toString(), Integer.parseInt(txtAddRating.getText().toString()), txtAddDescription.getText().toString(), txtAddImage.getText().toString());
                    Toast.makeText(AddingGamesActivity.this, "Game added",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddingGamesActivity.this, AddingGamesActivity.class);
                    startActivity(intent);
                }
                catch (Exception e ){
                    Toast.makeText(AddingGamesActivity.this, "Error adding new game",Toast.LENGTH_SHORT).show();
                    game = new Game (-1, "error","error",0, "error", "error");
                    Intent intent = new Intent(AddingGamesActivity.this, AddingGamesActivity.class);
                    startActivity(intent);
                }


                DBHelper dbHelper = new DBHelper(AddingGamesActivity.this);
                boolean successAdd = dbHelper.addGDB(game);
            }

        });


    }


    private void initViews(){
        btnAddNewGame = findViewById(R.id.btnAddNewGame);
        txtAddGameName = findViewById(R.id.txtAddGameName);
        txtAddDeveloper = findViewById(R.id.txtAddDeveloper);
        txtAddRating = findViewById(R.id.txtAddRating);
        txtAddDescription = findViewById(R.id.txtAddDescription );
        txtAddImage = findViewById(R.id.txtAddImage);

    }



}
