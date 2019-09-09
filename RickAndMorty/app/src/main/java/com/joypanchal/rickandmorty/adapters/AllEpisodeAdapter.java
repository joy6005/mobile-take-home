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

    // Provide a suitable constructor (depends on the kind of dataset)
    public AllEpisodeAdapter(List<Episode> episodes, Context ctx) {
        this.episodes = episodes;
        this.ctx = ctx;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AllEpisodeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_episode_row, parent, false);

        MyViewHolder vh = new MyViewHolder(contactView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtEpisodeName.setText(episodes.get(position).getName());
        holder.txtEpisode.setText(episodes.get(position).getEpisode());
        holder.txtEpisodeDate.setText(episodes.get(position).getAirDate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return episodes.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
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
