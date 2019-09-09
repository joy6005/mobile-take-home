package com.joypanchal.rickandmorty.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.adapters.AllCharactersAdapter;
import com.joypanchal.rickandmorty.callbacks.CharactersCallBackListener;
import com.joypanchal.rickandmorty.models.Episode;
import com.joypanchal.rickandmorty.models.EpisodeCharacter;
import com.joypanchal.rickandmorty.networks.HttpCharactersNetworkCalls;

import java.util.ArrayList;
import java.util.List;

public class CharacterListActivity extends AppCompatActivity implements CharactersCallBackListener {

    public List<Episode> episodes = new ArrayList<Episode>();
    public String allCharacthers = "";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allCharacthers = getIntent().getExtras().getString("AllCharacters");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String my_url = "https://rickandmortyapi.com/api/character/" + allCharacthers;

        if (isNetworkAvailable(this)) {
            new HttpCharactersNetworkCalls(this, my_url).execute();
        } else
            Toast.makeText(getApplicationContext(), "Internet not available.Please connect to Internet.", Toast.LENGTH_LONG).show();



    }

    @Override
    public void onSuccess(List<EpisodeCharacter> characterList) {
        recyclerView.setAdapter(new AllCharactersAdapter(characterList, CharacterListActivity.this));
        Log.d("data", "Success");
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
