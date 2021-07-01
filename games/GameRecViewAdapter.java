package com.example.games;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;
import static com.example.games.GameActivity.GAME_ID_KEY;

public class GameRecViewAdapter extends RecyclerView.Adapter<GameRecViewAdapter.ViewHolder> {

    private static final String TAG = "GameRecViewAdapter";

    private ArrayList<Game> games = new ArrayList<>();
    private Context mContext;
    private String parentActivity;



    public GameRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtGameName.setText(games.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(games.get(position).getImageUrl())
                .into(holder.imgGame);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send id to Game activity
                Intent intent = new Intent(mContext, GameActivity.class);
                intent.putExtra(GAME_ID_KEY, games.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.txtDev.setText(games.get(position).getDeveloper());
        holder.txtDescription.setText((games.get(position).getDescription()));


        // Logic for delete button in every activity

        if(parentActivity.equals("allGames")){
            holder.btnDeleteGame.setVisibility(View.GONE);
        }
        else if (parentActivity.equals("alreadyPlayed")){
            holder.btnDeleteGame.setVisibility(View.VISIBLE);
            holder.btnDeleteGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    DBHelper dbHelper = new DBHelper(mContext);
                    builder.setMessage("Are you sure u want to delete this game from the list?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbHelper.removeFromAlreadyPlayed(games.get(position))){
                                Toast.makeText(mContext,"Removed",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, AlreadyPlayedActivity.class);
                                startActivity(mContext, intent, null);

                            }
                            else { Toast.makeText(mContext,"Error",Toast.LENGTH_SHORT).show();
                                }
                        }


                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
            });
        }
        else if (parentActivity.equals("wantToPlay")){
            holder.btnDeleteGame.setVisibility(View.VISIBLE);
            holder.btnDeleteGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    DBHelper dbHelper = new DBHelper(mContext);
                    builder.setMessage("Are you sure u want to delete this game from the list?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbHelper.removeFromwantToPlayGames(games.get(position))){{
                                Toast.makeText(mContext,"Removed",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, WantToPlayActivity.class);
                                startActivity(mContext, intent, null);}
                            }
                            else { Toast.makeText(mContext,"Error",Toast.LENGTH_SHORT).show(); }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
            });
        }
        else if (parentActivity.equals("currentlyPlaying")){
            holder.btnDeleteGame.setVisibility(View.VISIBLE);
            holder.btnDeleteGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // User alert for when they want to delete something
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    DBHelper dbHelper = new DBHelper(mContext);
                    builder.setMessage("Are you sure u want to delete this game from the list?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbHelper.removeFromcurrentlyPlayingGames(games.get(position))){{
                                Toast.makeText(mContext,"Removed",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, CurrentlyPlaying.class);
                                startActivity(mContext, intent, null);}
                            }
                            else { Toast.makeText(mContext,"Error",Toast.LENGTH_SHORT).show(); }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
            });
        }
        else if (parentActivity.equals("favouriteGame")){
            holder.btnDeleteGame.setVisibility(View.VISIBLE);
            holder.btnDeleteGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    DBHelper dbHelper = new DBHelper(mContext);
                    builder.setMessage("Are you sure u want to delete this game from the list?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbHelper.removeFromfavouriteGames(games.get(position))){{
                                Toast.makeText(mContext,"Removed",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, FavoutiteActivity.class);
                                startActivity(mContext, intent, null);}
                            }
                            else { Toast.makeText(mContext,"Error",Toast.LENGTH_SHORT).show(); }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();                }
            });

        }


        // Extenting the card view

        if (games.get(position).getExtended()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRL.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRL.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgGame;
        private TextView txtGameName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRL;
        private TextView txtDev, txtDescription;
        private Button btnDeleteGame;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgGame = itemView.findViewById(R.id.imgGame);
            txtGameName = itemView.findViewById(R.id.txtGameName);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRL = itemView.findViewById(R.id.expandedRL);
            txtDev = itemView.findViewById(R.id.txtDev);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            btnDeleteGame = itemView.findViewById(R.id.btnDeleteGame);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game game = games.get(getAdapterPosition());
                    game.setExtended(!game.getExtended());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game game = games.get(getAdapterPosition());
                    game.setExtended(!game.getExtended());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
