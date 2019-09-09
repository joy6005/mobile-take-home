package com.joypanchal.rickandmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.models.EpisodeCharacter;
import com.joypanchal.rickandmorty.ui.CharacterDetailsActivity;

import java.util.List;

public class AllCharactersAdapter extends RecyclerView.Adapter<AllCharactersAdapter.MyViewHolder> {
    public static List<EpisodeCharacter> allCharacters;
    public static Context ctx;


    public AllCharactersAdapter(List<EpisodeCharacter> allCharacters, Context ctx) {
        this.allCharacters = allCharacters;
        this.ctx = ctx;
    }


    @Override
    public AllCharactersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View contactView = inflater.inflate(R.layout.list_episode_characters_row, parent, false);

        MyViewHolder vh = new MyViewHolder(contactView);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        EpisodeCharacter episodeCharacter = allCharacters.get(position);
        holder.txtCharacterName.setText(episodeCharacter.getName());
        holder.txtStatus.setText("Status : " + episodeCharacter.getStatus());
        holder.txtSpecies.setText("Species : " + episodeCharacter.getSpecies());
        holder.episodeCharacter = episodeCharacter;

        if (episodeCharacter.getStatus().equals("Dead")) {
            holder.txtStatus.setTextColor(Color.RED);
        } else if (episodeCharacter.getStatus().equals("Alive")) {
            holder.txtStatus.setTextColor(Color.GREEN);
        } else {
            holder.txtStatus.setTextColor(Color.BLUE);
        }

    }


    @Override
    public int getItemCount() {
        return allCharacters.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtCharacterName, txtStatus, txtSpecies;
        EpisodeCharacter episodeCharacter;

        public MyViewHolder(View v) {
            super(v);
            txtCharacterName = (TextView) v.findViewById(R.id.txtName);
            txtStatus = (TextView) v.findViewById(R.id.txtStatus);
            txtSpecies = (TextView) v.findViewById(R.id.txtSpecies);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(ctx, CharacterDetailsActivity.class);
            i.putExtra("character", episodeCharacter);
            ctx.startActivity(i);
        }
    }
}
