package com.joypanchal.rickandmorty.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.adapters.AllEpisodeAdapter;
import com.joypanchal.rickandmorty.callbacks.EpisodesCallBackListener;
import com.joypanchal.rickandmorty.models.Episode;
import com.joypanchal.rickandmorty.networks.HttpEpisodesNetworkCalls;

import java.util.ArrayList;
import java.util.List;

public class EpisodesListActivity extends AppCompatActivity implements EpisodesCallBackListener {

    public List<Episode> episodes = new ArrayList<Episode>();
    String allCharacters = "";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String my_url = "https://rickandmortyapi.com/api/episode/";

        if (isNetworkAvailable(this)) {
            HttpEpisodesNetworkCalls networkCalls = new HttpEpisodesNetworkCalls(this, my_url);
            networkCalls.execute();
        } else
            Toast.makeText(getApplicationContext(), "Internet not available.Please connect to Internet.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(List<Episode> episodes) {
        recyclerView.setAdapter(new AllEpisodeAdapter(episodes, EpisodesListActivity.this));
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getApplicationContext(), "Something wrong..!! Please try again", Toast.LENGTH_LONG).show();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
