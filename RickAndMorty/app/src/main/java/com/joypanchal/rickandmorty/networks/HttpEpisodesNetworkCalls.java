package com.joypanchal.rickandmorty.networks;

import android.os.AsyncTask;
import android.util.Log;

import com.joypanchal.rickandmorty.callbacks.EpisodesCallBackListener;
import com.joypanchal.rickandmorty.models.Episode;

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

public class HttpEpisodesNetworkCalls extends AsyncTask<String, Integer, List<Episode>> {

    public List<Episode> episodes;
    private String url;
    private EpisodesCallBackListener episodesCallBackListener;

    public HttpEpisodesNetworkCalls(EpisodesCallBackListener episodesCallBackListener, String url) {
        this.url = url;
        this.episodesCallBackListener = episodesCallBackListener;

        this.episodes = new ArrayList<Episode>();
    }

    @Override
    protected List<Episode> doInBackground(String... strings) {
        BufferedReader httpResponseReader = null;
        try {
            URL serverUrl = new URL(this.url);
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
                    return episodes;
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
    protected void onPostExecute(List<Episode> episodes) {
        super.onPostExecute(episodes);

        Log.d("oho", "onPostExecute");
        //recyclerView.setAdapter(new MyAdapter(episodes, MainActivity.this));

        if (episodes != null) {
            episodesCallBackListener.onSuccess(episodes);
        } else {
            episodesCallBackListener.onError("API call failes");
        }
    }

    private String getLastBitFromUrl(final String url) {
        // return url.replaceFirst("[^?]*/(.*?)(?:\\?.*)","$1);" <-- incorrect
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }
}
