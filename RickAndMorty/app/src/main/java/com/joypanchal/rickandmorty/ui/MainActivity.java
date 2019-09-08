package com.joypanchal.rickandmorty.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.joypanchal.rickandmorty.R;
import com.joypanchal.rickandmorty.adapters.MyAdapter;
import com.joypanchal.rickandmorty.callbacks.EpisodesCallBackListener;
import com.joypanchal.rickandmorty.models.Episode;
import com.joypanchal.rickandmorty.networks.HttpEpisodesNetworkCalls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EpisodesCallBackListener {

    public List<Episode> episodes = new ArrayList<Episode>();
    String allCharacters = "";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static String getLastBitFromUrl(final String url) {
        // return url.replaceFirst("[^?]*/(.*?)(?:\\?.*)","$1);" <-- incorrect
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }

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
        //new MyHttpRequestTask().execute(my_url);


        HttpEpisodesNetworkCalls networkCalls = new HttpEpisodesNetworkCalls(this, my_url);
        networkCalls.execute();

    }

    @Override
    public void onSuccess(List<Episode> episodes) {
        recyclerView.setAdapter(new MyAdapter(episodes, MainActivity.this));
    }

    @Override
    public void onError(String error) {

    }

    private class MyHttpRequestTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d("oho", "onPreExecute");
        }

        @Override
        protected String doInBackground(String... params) {
            String my_url = params[0];

            BufferedReader httpResponseReader = null;
            try {
                URL serverUrl = new URL(my_url);
                HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                httpResponseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String lineRead = httpResponseReader.readLine();

                if (lineRead != null || lineRead != "") {
                    try {
                        JSONObject obj = new JSONObject(lineRead);
                        JSONArray episodeData = obj.getJSONArray("episodes");
                        int n = episodeData.length();
                        for (int i = 0; i < n; ++i) {
                            JSONObject entity = episodeData.getJSONObject(i);
                            int id = entity.getInt("id");
                            String name = entity.getString("name");
                            String airDate = entity.getString("air_date");
                            String episode = entity.getString("episode");
                            JSONArray characters = entity.getJSONArray("characters");
                            List<String> chr = new ArrayList<String>();
                            for (int j = 0; j < characters.length(); j++) {
                                chr.add(getLastBitFromUrl(characters.getString(j)).toString());
                            }
                            String finalChr = String.join(",", chr);
                            String url = entity.getString("url");
                            String created = entity.getString("created");
                            episodes.add(new Episode(id, name, airDate, episode, finalChr, url, created));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {

                if (httpResponseReader != null) {
                    try {
                        httpResponseReader.close();
                    } catch (IOException ioe) {
                        // Close quietly
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("oho", "onPostExecute");
            recyclerView.setAdapter(new MyAdapter(episodes, MainActivity.this));
        }
    }
}
