package com.joypanchal.rickandmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.models.Episode;
import com.joypanchal.rickandmorty.ui.CharacterListActivity;

import java.util.List;

public class AllEpisodeAdapter extends RecyclerView.Adapter<AllEpisodeAdapter.MyViewHolder> {
    public static List<Episode> episodes;
    public static Context ctx;


    public AllEpisodeAdapter(List<Episode> episodes, Context ctx) {
        this.episodes = episodes;
        this.ctx = ctx;
    }


    @Override
    public AllEpisodeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_episode_row, parent, false);

        MyViewHolder vh = new MyViewHolder(contactView);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtEpisodeName.setText(episodes.get(position).getName());
        holder.txtEpisode.setText(episodes.get(position).getEpisode());
        holder.txtEpisodeDate.setText(episodes.get(position).getAirDate());
    }


    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtEpisodeName, txtEpisode, txtEpisodeDate;

        public MyViewHolder(View v) {
            super(v);
            txtEpisodeName = (TextView) v.findViewById(R.id.txtEpisodeName);
            txtEpisode = (TextView) v.findViewById(R.id.txtNumber);
            txtEpisodeDate = (TextView) v.findViewById(R.id.txtDate);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(ctx, CharacterListActivity.class);
            i.putExtra("AllCharacters", episodes.get(getAdapterPosition()).getCharacters());
            ctx.startActivity(i);
        }
    }
}
